package com.example.newsapp.ui.web_view

import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.domain.util.AppConstants
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentWebViewBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.util.Util


class WebViewFragment : BaseFragment<FragmentWebViewBinding>() {
    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!
    override val layoutId: Int
        get() = R.layout.fragment_web_view
    private var url: String ? =null

    override fun viewSetup() {
        _binding = viewDataBinding
         arguments?.let {
             url = it.getString(AppConstants.URL)
         }
        binding.webView.settings.also {
            it.loadsImagesAutomatically = true
            it.javaScriptEnabled = true
            it.useWideViewPort = true
            it.loadWithOverviewMode = true
            it.setSupportMultipleWindows(true)
            it.domStorageEnabled = true
        }
        binding.webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        url?.let {
            binding.webView.loadUrl(it)
        }
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                 Util.showLoading(requireContext())
                return super.shouldOverrideUrlLoading(view, request)
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Util.dismissLoading()
            }
        }


    }

    override fun viewModelSetup() {
    }

}


