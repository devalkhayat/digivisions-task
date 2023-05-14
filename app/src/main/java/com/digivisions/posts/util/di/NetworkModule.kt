package com.digivisions.posts.util.di

import com.digivisions.data.common.RetrofitBuilder
import org.koin.dsl.module

val NetworkModule = module {
    single { RetrofitBuilder() }
}