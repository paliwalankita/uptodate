package com.ankita.newsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankita.newsapplication.R
import com.ankita.newsapplication.adapters.NewsAdapters
import com.ankita.newsapplication.databinding.FragmentSavedNewsBinding

class SavedNewsFragment : Fragment() {
    lateinit var binding: FragmentSavedNewsBinding
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapters: NewsAdapters

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setUpRecyclerView()

        newsAdapters.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_webFragment, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setUpRecyclerView(){
        newsAdapters = NewsAdapters()
        binding.rvSavedNews.apply {
            adapter = newsAdapters
            layoutManager = LinearLayoutManager(activity)
        }
    }
}