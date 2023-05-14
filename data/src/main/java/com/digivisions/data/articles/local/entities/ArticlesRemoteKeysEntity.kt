package com.digivisions.data.articles.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles_remote_keys_table")
data class ArticlesRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val prevPage:Int?,
    val nextPage:Int?
)
