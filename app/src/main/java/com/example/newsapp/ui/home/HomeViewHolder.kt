package com.example.newsapp.ui.home

import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseViewHolder
import com.example.newsapp.ui.util.Util


class HomeViewHolder(private val itemBinding: ViewBinding) : BaseViewHolder<ItemArticleBinding, ArticleModel>(
    itemBinding
) {
    override fun onBind(position: Int, model: ArticleModel , searchQuery: String?) {
        Glide.with(itemBinding.root)
            .load(model.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.articleImage)
        binding.articleDesc.text = model.title
        binding.articleAuthor.text = model.author
        binding.articleDate.text= model.publishedAt?.let { Util.covertDate(it) }
        if(searchQuery != null){
            setHighLightedText(binding.articleDesc , searchQuery)
        }
    }

    override fun onDetached() {
    }

    override fun onViewRecycled() {
    }

    private fun setHighLightedText(tv: TextView, textToHighlight: String) {
        val tvt = tv.text.toString().lowercase()
        var ofe = tvt.indexOf(textToHighlight, 0)
        val wordToSpan: Spannable = SpannableString(tv.text)
        var ofs = 0
        while (ofs < tvt.length && ofe != -1) {
            ofe = tvt.indexOf(textToHighlight, ofs)
            if (ofe == -1) break else {
                // set color here
                wordToSpan.setSpan(BackgroundColorSpan(-0x100),
                    ofe,
                    ofe + textToHighlight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE)
            }
            ofs = ofe + 1
        }
    }
}