package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.newsapp.RetrofitInstance.newsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        fetchNewsArticles()
    }
//    private fun fetchNewsArticles() {
//        val call: Call<NewsResponse> =
//            newsApi.getTopNewsByCategory("general") // Using `api` directly
//        call.enqueue(object : Callback<NewsResponse> {
//            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
//                if (response.isSuccessful) {
//                    val articles = response.body()?.articles
//                    articles?.forEach { article ->
//                        Log.d(
//                            "MainActivity",
//                            "Title: ${article.title}, Description: ${article.description}"
//                        )
//                    }
//                } else {
//                    Log.e("MainActivity", "Failed to fetch news articles: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                Log.e("MainActivity", "Exception occurred: ${t.message}")
//            }
//        })
//    }
}