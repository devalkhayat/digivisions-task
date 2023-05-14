package com.digivisions.posts.util.di



import androidx.room.Room
import com.digivisions.data.articles.repoImp.ArticlesRepoImpl
import com.digivisions.data.common.Constants.DATABASE_NAME
import com.digivisions.data.common.LocalDatabase
import com.digivisions.domain.articles.repositories.ArticlesRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val LocalModule= module {

    single {
        Room.databaseBuilder(
            context = androidContext(),
            LocalDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}