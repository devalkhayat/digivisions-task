package com.digivisions.data.articles.remote

import com.digivisions.domain.articles.responses.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoints {

    @GET("everything")
    suspend fun getAllArticles(@Query("q") word:String,@Query("page") page:Int,@Query("pageSize") pageSize:Int):Response<ArticlesResponse>
}