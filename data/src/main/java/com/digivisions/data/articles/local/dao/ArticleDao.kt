package com.digivisions.data.articles.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.digivisions.domain.articles.models.Article

@Dao
interface ArticleDao {

     @Query("SELECT * FROM articles_table")
     fun getAllArticles():PagingSource<Int,Article>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertArticles(list:List<Article>)

     @Query("DELETE FROM articles_table")
     suspend fun deleteArticles()


}