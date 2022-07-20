package com.example.newsapp.ui.util

import android.content.Context
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class AppWebViewClients(val context: Context) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        context.let { Util.showLoading(it) }
        return super.shouldOverrideUrlLoading(view, request)
    }
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        Util.dismissLoading()
    }
}