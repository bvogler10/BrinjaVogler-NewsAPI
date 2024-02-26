package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentNewsListBinding


class NewsListFragment : Fragment(){

    private var _binding: FragmentNewsListBinding? = null
    private lateinit var adapter: NewsListAdapter
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val newsListViewModel: NewsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        binding.NewsRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NewsListAdapter(emptyList())

        // Set the item click listener
        adapter.setOnItemClickListener { article ->
            val bundle = Bundle().apply {
                // Pass individual parameters
                putString("title", article.title)
                putString("content", article.content)
                putString("author", article.author)
            }
            // Navigate to NewsDetailFragment using NavController
            findNavController().navigate(
                R.id.action_newsListFragment_to_articleDetailFragment,
                bundle
            )
        }

        val categorySpinner = binding.newsCategories
        val categoryList = listOf("General", "Business", "Entertainment", "Health", "Science", "Sports", "Technology") // Example category list
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categoryList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCategory: String = categoryList[position]
                newsListViewModel.getNewsByCategory(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        binding.NewsRecyclerView.adapter = adapter
        newsListViewModel.articles.observe(viewLifecycleOwner) { newsList ->
            adapter.updateNewsList(newsList)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}