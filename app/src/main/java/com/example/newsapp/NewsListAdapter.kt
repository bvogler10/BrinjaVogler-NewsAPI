package com.example.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ListItemNewsBinding


class NewsHolder(
    private val binding: ListItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.articleTitle.text = article.title
        binding.articleDescription.text = article.description.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${article.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class NewsListAdapter(
    private var articles: List<Article>
    ): RecyclerView.Adapter<NewsHolder>() {
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