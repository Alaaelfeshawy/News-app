package com.example.domain.model.home.response
import com.example.domain.model.home.Article

data class HomeResponse (
    var status :String ? =null,
    var source : String? = null,
    var sortBy : String ?= null,
    var articles: List<Article> ? =null
)