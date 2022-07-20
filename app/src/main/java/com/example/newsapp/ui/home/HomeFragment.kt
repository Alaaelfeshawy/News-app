package com.example.newsapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.domain.util.AppConstants
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseAdapter
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val adapter: BaseAdapter<ArticleModel,ItemArticleBinding> by lazy {
        BaseAdapter(R.layout.item_article,{
            val bundle = Bundle()
            bundle.putParcelable(AppConstants.ARTICLE_MODEL,it)
            navController?.navigate(R.id.action_homeFragment_to_newsDetailsFragment , bundle)
        }){
            HomeViewHolder(it)
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun viewSetup() {
        _binding = viewDataBinding
        viewModel.getHomeData()
        binding.articlesRecyclerView.adapter = adapter
        binding.tryAgain.setOnClickListener {viewModel.getHomeData()}
    }

    override fun viewModelSetup() {
        viewModel.homeData.observe(viewLifecycleOwner){
            it?.let {
                adapter.setDataList(it)
            }
        }
        viewModel.stateListener.loading.observe(viewLifecycleOwner){
            it?.let {
               if(it){
                   binding.noInternetLayout.visibility=View.GONE
                   Util.showLoading(requireContext())
               }else{
                   Util.dismissLoading()
               }
            }
        }
        viewModel.stateListener.errorMessage.observe(viewLifecycleOwner){
            message -> message?.let {
            makeToast(getString(it),Toast.LENGTH_SHORT)
            }
        }
        viewModel.error.observe(viewLifecycleOwner){
                message -> message?.let {
            makeToast(it,Toast.LENGTH_SHORT)
        }
        }
        viewModel.noInternet.observe(viewLifecycleOwner){
            it?.let {
                if(it){
                    binding.noInternetLayout.visibility=View.VISIBLE
                    binding.articlesRecyclerView.visibility=View.GONE
                }else{
                    binding.noInternetLayout.visibility=View.GONE
                    binding.articlesRecyclerView.visibility=View.VISIBLE
                }
            }
        }
    }
}