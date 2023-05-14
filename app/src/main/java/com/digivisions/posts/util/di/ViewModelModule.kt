package com.digivisions.posts.util.di

import com.digivisions.posts.features.articles.viewmodels.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {
    viewModel{ ArticlesViewModel(get()) }
}