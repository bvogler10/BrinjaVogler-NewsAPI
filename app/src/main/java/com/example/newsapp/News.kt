package com.example.newsapp

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
)

data class Article (
    val source: Source,
    val content: String,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val publishedAt: String,
    val urlToImage: String
    )

data class Source(
    val id: String?,
    val name: String
)