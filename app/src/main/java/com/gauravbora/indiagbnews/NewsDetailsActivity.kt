package com.gauravbora.indiagbnews

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        webView=findViewById(R.id.newsWebView)
        progressBar=findViewById(R.id.progressBar)

        val url:String?=intent.getStringExtra("URL")
        if(url!=null){
            webView.settings.javaScriptEnabled=true
            // nextLine is for    Force mobile version of website for WebView
            webView.settings.userAgentString="Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            webView.webViewClient=object: WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility= View.GONE
                    webView.visibility=View.VISIBLE
                }
            }
            webView.loadUrl(url)

        }

    }
}