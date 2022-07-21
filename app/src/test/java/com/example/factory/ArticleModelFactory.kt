package com.example.factory

import com.example.domain.model.home.Article
import com.example.newsapp.model.home.ArticleModel

object ArticleModelFactory {
    const val NUM_ATTRIBUTES=6

    fun generateDataForArticleModel(): ArticleModel {
        return ArticleModel(
            "author",
            "title",
            "des",
            "url",
            "urlToImage",
            "publishedAt",
        )
    }

    fun generateDataForArticleDomain(): Article {
        return Article(
            "author",
            "title",
            "des",
            "url",
            "urlToImage",
            "publishedAt",
        )
    }
}