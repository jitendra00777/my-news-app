package com.jitendra.mynewsapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jitendra.mynewsapp.R
import com.jitendra.mynewsapp.databinding.NewsRowLayoutBinding
import com.jitendra.mynewsapp.model.Articles
import com.squareup.picasso.Picasso

class NewsAdapter(private var articles: List<Articles>, private val onArticleSelected: OnArticleSelected) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    private lateinit var binding: NewsRowLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        binding = NewsRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        binding.tvTitle.text = articles[position].title
        binding.tvDescription.text = articles[position].description
        if (articles[position].author != null)
            binding.tvAuthor.text = "By ${articles[position].author}"
        Picasso.get()
            .load(articles[position].urlToImage)
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
            .into(binding.ivNews)
        Log.d("title", "" + articles[position].title)
        binding.cvNews.setOnClickListener {
            onArticleSelected.onArticleSelected(
                articles[position]
            )
        }
    }

    override fun getItemCount() = articles.size

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setArticles(articles: List<Articles>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    interface OnArticleSelected{
        fun onArticleSelected(articles: Articles)
    }
}