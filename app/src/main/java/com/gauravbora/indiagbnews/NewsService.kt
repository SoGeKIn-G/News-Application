package com.gauravbora.indiagbnews

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/ v2/top-headlines?country=us&apiKey=210fdce7be3049d0ab1121f04c1b6edc

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=210fdce7be3049d0ab1121f04c1b6edc

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "210fdce7be3049d0ab1121f04c1b6edc"

interface NewsInterface {

    @GET("v2/top-headlines?apikey=$API_KEY")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int
    ): retrofit2.Call<News>


}



object NewsServices {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        Log.d("Retrofit obj","Createddddd")

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}