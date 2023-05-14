package com.digivisions.data.articles.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.digivisions.data.articles.local.entities.ArticlesRemoteKeysEntity

@Dao
interface ArticlesRemoteKeysDao {

     @Query("SELECT * FROM articles_remote_keys_table WHERE id=:id")
     fun getRemoteKeys(id: String):ArticlesRemoteKeysEntity

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addAllRemoteKeys(remoteKeys:List<ArticlesRemoteKeysEntity>)

     @Query("DELETE FROM articles_remote_keys_table")
     suspend fun deleteAllRemoteKeys()

     
}