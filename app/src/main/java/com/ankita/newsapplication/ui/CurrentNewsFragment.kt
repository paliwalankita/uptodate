package com.ankita.newsapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankita.newsapplication.R
import com.ankita.newsapplication.adapters.NewsAdapters
import com.ankita.newsapplication.databinding.FragmentCurrentNewsBinding
import com.ankita.newsapplication.utils.Resource

class CurrentNewsFragment : Fragment() {
    lateinit var binding: FragmentCurrentNewsBinding
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapters: NewsAdapters

    val TAG = "CurrentNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        newsAdapters.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_currentNewsFragment_to_webFragment, bundle)
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapters.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setUpRecyclerView(){
        newsAdapters = NewsAdapters()
        binding.rvCurrentNews.apply {
            adapter = newsAdapters
            layoutManager = LinearLayoutManager(activity)
        }
    }
}