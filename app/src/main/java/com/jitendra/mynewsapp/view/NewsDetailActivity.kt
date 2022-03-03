package com.jitendra.mynewsapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jitendra.mynewsapp.R
import com.jitendra.mynewsapp.databinding.ActivityNewsDetailBinding
import com.jitendra.mynewsapp.model.Articles
import com.jitendra.mynewsapp.util.NewsUtil
import com.jitendra.mynewsapp.viewmodel.NewsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding
    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var articleID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val article = intent.getSerializableExtra(NewsUtil.ARTICLE) as Articles
        articleID = newsViewModel.getArticleID(article.url)
        setUI(article)

        newsViewModel.likeLiveData.observe(this, Observer { likes ->
            binding.tvLikes.text = likes.count.toString()
        })

        newsViewModel.commentsLiveData.observe(this, Observer { comments ->
            binding.tvComments.text = comments.count.toString()
        })
        newsViewModel.fetchLikes("${NewsUtil.LIKE_BASE_URL}$articleID")
        newsViewModel.fetchComments("${NewsUtil.COMMENT_BASE_URL}.$articleID")

        binding.btFullArticle.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(NewsUtil.URL, article.url)
            startActivity(intent)
        })
    }

    private fun setUI(articles: Articles) {
        binding.tvTitle.text = articles.title
        binding.tvDescription.text = articles.content
        if (articles.author != null)
            binding.tvAuthor.text = "By ${articles.author}"
        Picasso.get()
            .load(articles.urlToImage)
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
            .into(binding.ivNews)
    }
}