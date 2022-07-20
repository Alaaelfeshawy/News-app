package com.example.newsapp.ui.news_details

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.domain.util.AppConstants
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.util.Util

class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding>() {
    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!
    private var articleModel : ArticleModel ?= null
    override val layoutId: Int
        get() = R.layout.fragment_news_details

    override fun viewSetup() {
        _binding = viewDataBinding
        arguments?.let {
            articleModel = it.getParcelable(AppConstants.ARTICLE_MODEL)
        }
        Glide.with(requireContext())
            .load(articleModel?.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.articleImage)
        binding.articleTitle.text = articleModel?.title
        binding.articleDesc.text = articleModel?.description
        binding.articleAuthor.text = articleModel?.author
        binding.articleDate.text= articleModel?.publishedAt?.let { Util.covertDate(it) }
        binding.openWebView.setOnClickListener {
            val bundle  = Bundle()
            bundle.putString(AppConstants.URL,articleModel?.url)
            navController?.navigate(R.id.action_newsDetailsFragment_to_webViewFragment , bundle)
        }
    }

    override fun viewModelSetup() {
    }

}