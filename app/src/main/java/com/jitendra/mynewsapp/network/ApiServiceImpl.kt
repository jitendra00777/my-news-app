package com.jitendra.mynewsapp.network

import com.jitendra.mynewsapp.model.Comments
import com.jitendra.mynewsapp.model.Likes
import com.jitendra.mynewsapp.model.News
import javax.inject.Inject
import javax.inject.Named

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    @Inject
    @Named("API_KEY")
    lateinit var apiKey: String
    suspend fun getNews(
    ): News = apiService.getNews(apiKey)

    suspend fun getLikes(url: String): Likes = apiService.getLikes(url)

    suspend fun getComments(url: String): Comments = apiService.getComments(url)
}