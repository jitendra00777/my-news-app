package com.jitendra.mynewsapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jitendra.mynewsapp.R
import com.jitendra.mynewsapp.databinding.ActivityMainBinding
import com.jitendra.mynewsapp.model.Articles
import com.jitendra.mynewsapp.util.NewsUtil
import com.jitendra.mynewsapp.view.adapter.NewsAdapter
import com.jitendra.mynewsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NewsAdapter.OnArticleSelected {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter

    @Inject
    @Named("API_KEY")
    lateinit var apiKey: String
    private val newsViewModel:NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerview()

        newsViewModel.getNewsObserver().observe(this, Observer { news ->
            if (news != null){
                newsAdapter.setArticles(news.articles)
            }else{
                Toast.makeText(this,getString(R.string.oops_something_went_wrong),Toast.LENGTH_LONG).show()
            }
        })

        newsViewModel.fetchNews()

    }

    private fun initRecyclerview(){
        newsAdapter= NewsAdapter(ArrayList(),this)
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=newsAdapter
        }
    }

    override fun onArticleSelected(articles: Articles) {
        val intent = Intent(this,NewsDetailActivity::class.java)
        intent.putExtra(NewsUtil.ARTICLE,articles)
        startActivity(intent)
    }
}