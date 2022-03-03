package com.jitendra.mynewsapp.repository

import com.jitendra.mynewsapp.model.Comments
import com.jitendra.mynewsapp.model.Likes
import com.jitendra.mynewsapp.model.News
import com.jitendra.mynewsapp.network.ApiServiceImpl
import javax.inject.Inject

class NewsRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    suspend fun getNews(): News = apiServiceImpl.getNews()

    suspend fun getLikes(url: String): Likes = apiServiceImpl.getLikes(url)

    suspend fun getComments(url: String): Comments = apiServiceImpl.getComments(url)

}