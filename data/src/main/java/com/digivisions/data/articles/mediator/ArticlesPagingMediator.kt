package com.digivisions.data.articles.mediator

import android.util.Log
import androidx.paging.*
import androidx.room.withTransaction
import com.digivisions.data.articles.local.entities.ArticlesRemoteKeysEntity
import com.digivisions.data.articles.remote.EndPoints
import com.digivisions.data.common.Constants.DEFAULT_PAGE_SIZE
import com.digivisions.data.common.LocalDatabase
import com.digivisions.domain.articles.models.Article

@ExperimentalPagingApi
class ArticlesPagingMediator(private val  localDatabase: LocalDatabase, private val endPoints: EndPoints):RemoteMediator<Int,Article>() {

    private val articleDao=localDatabase.articlesDao()
    private val remoteKeysDao=localDatabase.articlesRemoteKeysDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {


         return try {


             val currentPage = when (loadType) {
                 LoadType.REFRESH -> {
                     val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                     remoteKeys?.nextPage?.minus(1) ?: 1
                 }
                 LoadType.PREPEND -> {
                     val remoteKeys = getRemoteKeyForFirstItem(state)
                     val prevPage = remoteKeys?.prevPage
                         ?: return MediatorResult.Success(
                             endOfPaginationReached = remoteKeys != null
                         )
                     prevPage
                 }
                 LoadType.APPEND -> {
                     val remoteKeys = getRemoteKeyForLastItem(state)
                     val nextPage = remoteKeys?.nextPage
                         ?: return MediatorResult.Success(
                             endOfPaginationReached = remoteKeys != null
                         )
                     nextPage
                 }
             }

             val response = endPoints.getAllArticles(word="bitcoin",page = currentPage, pageSize = DEFAULT_PAGE_SIZE)
             val endOfPaginationReached = response.body()?.articles?.isEmpty()

             val prevPage = if (currentPage == 1) null else currentPage - 1
             val nextPage = if (endOfPaginationReached == true) null else currentPage + 1

             localDatabase.withTransaction {
                 if (loadType == LoadType.REFRESH) {
                     articleDao.deleteArticles()
                     remoteKeysDao.deleteAllRemoteKeys()
                 }
                 val keys = response.body()?.articles?.map { article ->
                     ArticlesRemoteKeysEntity(
                         id = article.title!!,
                         prevPage = prevPage,
                         nextPage = nextPage
                     )
                 }
                 remoteKeysDao.addAllRemoteKeys(remoteKeys = keys!!)
                 articleDao.insertArticles(  response.body()!!.articles)
             }
             MediatorResult.Success(endOfPaginationReached = endOfPaginationReached!!)

         }catch (ex:Exception){

             return MediatorResult.Error(ex)
         }

    }



    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>
    ): ArticlesRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.title?.let { t ->
                remoteKeysDao.getRemoteKeys(id = t)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Article>
    ): ArticlesRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { art ->
                remoteKeysDao.getRemoteKeys(id = art.title!!)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Article>
    ): ArticlesRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { art ->
                remoteKeysDao.getRemoteKeys(id = art.title!!)
            }
    }



}