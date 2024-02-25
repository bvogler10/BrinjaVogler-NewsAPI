package com.example.newsapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//
//    companion object {
//        fun newInstance() = NewsListFragment()
//    }

    private lateinit var viewModel: NewsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)

        binding.NewsRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
        //return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                newsListViewModel.crimes.collect { crimes ->
//                    binding.NewsRecyclerView.adapter =
//                        CrimeListAdapter(crimes) { crimeId ->
//                            findNavController().navigate(
//                                CrimeListFragmentDirections.showCrimeDetail(crimeId)
//                            )
                        //}
                //}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}