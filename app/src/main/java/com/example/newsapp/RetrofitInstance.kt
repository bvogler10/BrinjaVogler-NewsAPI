package com.example.newsapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RetrofitInstance"

object RetrofitInstance {
    private const val BASE_URL = "https://newsapi.org/v2/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    internal val newsApi: NewsAPIService = retrofit.create(NewsAPIService::class.java)
}