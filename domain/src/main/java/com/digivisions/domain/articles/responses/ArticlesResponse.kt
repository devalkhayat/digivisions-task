package com.digivisions.domain.articles.responses

import com.digivisions.domain.articles.models.Article
import com.google.gson.annotations.SerializedName

data class ArticlesResponse (
  @SerializedName("status"       ) var status       : String?             = null,
  @SerializedName("totalResults" ) var totalResults : Int?                = null,
  @SerializedName("articles"     ) var articles     : ArrayList<Article> = arrayListOf()
)

