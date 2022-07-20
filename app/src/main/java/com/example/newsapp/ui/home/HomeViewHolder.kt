package com.example.newsapp.ui.home

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseViewHolder
import com.example.newsapp.ui.util.Util

class HomeViewHolder(private val itemBinding: ViewBinding) : BaseViewHolder<ItemArticleBinding, ArticleModel>(
    itemBinding
) {
    override fun onBind(position: Int, model: ArticleModel) {
        Glide.with(itemBinding.root)
            .load(model.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.articleImage)
        binding.articleDesc.text = model.description
        binding.articleAuthor.text = model.author
        binding.articleDate.text= model.publishedAt?.let { Util.covertDate(it) }
    }

    override fun onDetached() {
    }

    override fun onViewRecycled() {
    }
}