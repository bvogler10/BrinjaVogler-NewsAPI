package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.databinding.FragmentArticleDetailBinding
import com.example.newsapp.databinding.FragmentNewsListBinding

class ArticleDetailFragment: Fragment() {
    private var _binding: FragmentArticleDetailBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title")
        val author = arguments?.getString("author")
        val content = arguments?.getString("content")

        view.findViewById<TextView>(R.id.article_title).text = title
        view.findViewById<TextView>(R.id.article_content).text = content
        view.findViewById<TextView>(R.id.author).text = author
        val backButton = view.findViewById<Button>(R.id.button)
        backButton.setOnClickListener {
            // Handle back button click, for example, navigate back
            findNavController().navigateUp()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}