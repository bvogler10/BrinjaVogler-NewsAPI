package com.example.newsapp

import android.net.http.HttpException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.io.IOException
import retrofit2.Response


class NewsListViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    init {
        viewModelScope.launch {
            try {
                // Perform the network call in the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.newsApi.getTopNewsByCategory("general").execute()
                }

                if (response.isSuccessful) {
                    val articlesList = response.body()
                    if (articlesList != null) {
                        // Update LiveData on the main thread
                        _articles.postValue(articlesList.articles)
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("NewsListViewModel", "Unsuccessful response: ${response.code()}")
                }
            } catch (e: IOException) {
                // Handle IO exception
                Log.e("NewsListViewModel", "IO Exception: ${e.message}", e)
            }
        }
    }

    fun getNewsByCategory(category: String) {
        viewModelScope.launch {
            try {
                // Perform the network call in the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.newsApi.getTopNewsByCategory(category).execute()
                }

                if (response.isSuccessful) {
                    val articlesList = response.body()
                    if (articlesList != null) {
                        // Update LiveData on the main thread
                        _articles.postValue(articlesList.articles)
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("NewsListViewModel", "Unsuccessful response: ${response.code()}")
                }
            } catch (e: IOException) {
                // Handle IO exception
                Log.e("NewsListViewModel", "IO Exception: ${e.message}", e)
            }
        }
    }
}
