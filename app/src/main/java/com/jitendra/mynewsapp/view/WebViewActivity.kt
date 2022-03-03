package com.jitendra.mynewsapp.view

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.jitendra.mynewsapp.databinding.ActivityWebViewBinding
import com.jitendra.mynewsapp.util.NewsUtil

class WebViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url:String = intent.getStringExtra(NewsUtil.URL).toString()
        loadURL(url)
    }

    private fun loadURL(url: String){
        binding.wvFullArticle.webViewClient = WebViewClient()
        binding.wvFullArticle.loadUrl(url)
        binding.wvFullArticle.settings.javaScriptEnabled = true
        binding.wvFullArticle.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        if (binding.wvFullArticle.canGoBack())
            binding.wvFullArticle.goBack()
        else
            super.onBackPressed()
    }
}