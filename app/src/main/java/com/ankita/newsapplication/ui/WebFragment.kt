package com.ankita.newsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.ankita.newsapplication.R
import com.ankita.newsapplication.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    lateinit var binding: FragmentWebBinding
    lateinit var viewModel: NewsViewModel
    val args: WebFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWebBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}