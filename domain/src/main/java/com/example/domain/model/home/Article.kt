package com.example.domain.model.home

data class Article (
    var author: String?= null,
    var title: String? = null,
    var description : String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String?  = null
)