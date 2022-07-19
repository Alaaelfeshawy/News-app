package com.example.newsapp.model.home

data class ArticleModel (
    var author: String?= null,
    var title: String? = null,
    var description : String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String?  = null
)