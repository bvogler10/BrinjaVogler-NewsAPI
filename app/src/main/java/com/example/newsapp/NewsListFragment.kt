package com.example.newsapp

import android.R
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentNewsListBinding
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch


private const val TAG = "NewsListFragment"

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val newsListViewModel: NewsListViewModel by viewModels()


    companion object {
        fun newInstance() = NewsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)

        binding.NewsRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NewsListAdapter(emptyList())
        binding.NewsRecyclerView.adapter = adapter
        Log.d("NewsListFragment", "i am in newslistfragment")

        val categorySpinner = binding.newsCategories
        val categoryList = listOf("General", "Business", "Entertainment", "Health", "Science", "Sports", "Technology") // Example category list
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, categoryList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter


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