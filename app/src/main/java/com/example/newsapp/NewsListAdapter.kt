package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ListItemNewsBinding




class NewsListAdapter(
    private var articles: List<Article>,
    ): RecyclerView.Adapter<NewsListAdapter.NewsHolder>() {

    private lateinit var onItemClick: (Article) -> Unit

    inner class NewsHolder(
        private val binding: ListItemNewsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.articleTitle.text = article.title
            binding.articleDescription.text = article.description.toString()

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(articles[position])
                }
            }
        }

    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClick = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNewsBinding.inflate(inflater, parent, false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news = articles[position]
        holder.bind(news)
    }
    fun updateNewsList(refreshList: List<Article>) {
        articles = refreshList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return articles.size
    }

    }