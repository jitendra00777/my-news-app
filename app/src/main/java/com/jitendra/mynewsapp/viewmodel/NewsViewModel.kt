package com.jitendra.mynewsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jitendra.mynewsapp.model.Comments
import com.jitendra.mynewsapp.model.Likes
import com.jitendra.mynewsapp.model.News
import com.jitendra.mynewsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject
constructor(private val newsRepository: NewsRepository) : ViewModel() {
    var newsLiveData: MutableLiveData<News> = MutableLiveData()
    var likeLiveData: MutableLiveData<Likes> = MutableLiveData()
    var commentsLiveData: MutableLiveData<Comments> = MutableLiveData()

    fun getNewsObserver() = newsLiveData

    fun getLikesObserver() = likeLiveData
    fun getCommentsObserver() = commentsLiveData

    fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsRepository.getNews()
                newsLiveData.postValue(response)
            } catch (e: Exception) {
                Log.e("Error",e.stackTrace.toString())
            }
        }
    }

    fun getArticleID( url: String?) : String{
        var articleID =""
        url?.let {
             articleID= it.replace("https://","")
            articleID = articleID.replace("/","-")
        }
        return articleID
    }

    fun fetchLikes(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsRepository.getLikes(url)
                likeLiveData.postValue(response)
            } catch (e: Exception) {
                Log.e("Error",e.stackTrace.toString())
            }
        }
    }

    fun fetchComments(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsRepository.getComments(url)
                commentsLiveData.postValue(response)
            } catch (e: Exception) {
                Log.e("Error",e.stackTrace.toString())
            }
        }
    }
}