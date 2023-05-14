package com.digivisions.data.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.digivisions.data.articles.local.dao.ArticleDao
import com.digivisions.data.articles.local.dao.ArticlesRemoteKeysDao
import com.digivisions.data.articles.local.entities.ArticlesRemoteKeysEntity
import com.digivisions.domain.articles.models.Article

@Database(entities = [Article::class, ArticlesRemoteKeysEntity::class], version = 1)
abstract class LocalDatabase:RoomDatabase() {
    abstract fun articlesDao(): ArticleDao
    abstract fun articlesRemoteKeysDao(): ArticlesRemoteKeysDao
}