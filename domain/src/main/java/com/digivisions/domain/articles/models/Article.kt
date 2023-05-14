package com.digivisions.domain.articles.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "articles_table")
data class Article (
  @PrimaryKey(autoGenerate = false)
  @SerializedName("title"       ) var title       : String,
  @SerializedName("urlToImage"  ) var urlToImage  : String? = null,
): Parcelable