package com.gauravbora.indiagbnews


import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    lateinit var newsList:RecyclerView
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResults = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsList=findViewById(R.id.newsList)


        adapter = NewsAdapter(this@MainActivity, articles)
        newsList.adapter = adapter
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        if(totalResults> (newsList.layoutManager as LinearLayoutManager).itemCount  && (newsList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()>= (newsList.layoutManager as LinearLayoutManager).itemCount-5){
            pageNum++
            getNews()
        }


  getNews()
    }


    private fun getNews() {

        val news :Call<News> = NewsServices.newsInstance.getHeadlines("in",pageNum)
        news.enqueue(object : Callback<News> {

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? =response.body()

                if (news != null) {
//                    Log.d("news","${news.articles}")

                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                    Log.d("news1234", news.toString())
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("news1234", "Error in Fetching News", t)
            }

        })
    }
}



