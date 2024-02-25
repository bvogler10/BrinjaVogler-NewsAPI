package com.example.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale.Category

interface NewsAPIService {
    @GET("top-headlines?country=us&apiKey=1e8c37a7c071451b8097796ffbd55e5b")
    fun getTopNewsByCategory(@Query("category") category: String): Call<NewsResponse>
}