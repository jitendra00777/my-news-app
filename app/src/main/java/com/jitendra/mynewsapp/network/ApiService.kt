package com.jitendra.mynewsapp.network

import com.jitendra.mynewsapp.model.Comments
import com.jitendra.mynewsapp.model.Likes
import com.jitendra.mynewsapp.model.News
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("top-headlines?country=us")
    suspend fun getNews(@Query("apiKey") apiKey: String
    ):News

    @GET
    suspend fun getLikes(@Url  url: String
    ): Likes

    @GET
    suspend fun getComments(@Url  url: String
    ): Comments
}