package com.digivisions.domain.articles.repositories

import androidx.paging.PagingData
import com.digivisions.domain.articles.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepo {

    suspend fun getAll(): Flow<PagingData<Article>>
}