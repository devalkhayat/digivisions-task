package com.digivisions.posts.util.di

import com.digivisions.domain.articles.usecases.GetAllArticlesUseCase
import org.koin.dsl.module

val UseCaseModule= module {
    single<GetAllArticlesUseCase> {GetAllArticlesUseCase(get())  }
}