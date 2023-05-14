package com.digivisions.data.articles.repoImp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.digivisions.data.articles.mediator.ArticlesPagingMediator
import com.digivisions.data.articles.remote.EndPoints
import com.digivisions.data.common.Constants.DEFAULT_PAGE_SIZE
import com.digivisions.data.common.LocalDatabase
import com.digivisions.data.common.RetrofitBuilder
import com.digivisions.domain.articles.models.Article
import com.digivisions.domain.articles.repositories.ArticlesRepo
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class ArticlesRepoImpl(private val retrofitBuilder: RetrofitBuilder,private val localDatabase:LocalDatabase): ArticlesRepo {


    private val endPoints=retrofitBuilder.start()?.create(EndPoints::class.java)

    override suspend fun getAll(): Flow<PagingData<Article>> {

        return Pager(
            config= PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            remoteMediator = ArticlesPagingMediator(
                endPoints = endPoints!!,
                localDatabase = localDatabase
            ),
            pagingSourceFactory =  { localDatabase.articlesDao().getAllArticles() }
        ).flow

    }
}