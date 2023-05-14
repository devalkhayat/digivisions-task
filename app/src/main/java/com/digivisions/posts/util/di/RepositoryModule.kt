package com.digivisions.posts.util.di

import androidx.paging.ExperimentalPagingApi
import com.digivisions.data.articles.repoImp.ArticlesRepoImpl
import com.digivisions.domain.articles.repositories.ArticlesRepo
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val RepositoryModule=module{

    single<ArticlesRepo> { ArticlesRepoImpl(get(),get()) }
}