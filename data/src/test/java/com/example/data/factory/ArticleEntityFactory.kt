package com.example.data.factory

import com.example.data.entity.home.ArticleEntity
import com.example.domain.model.home.Article

object ArticleEntityFactory {
    const val NUM_ATTRIBUTES=6

    fun generateDataForArticleEntity(): ArticleEntity {
        return ArticleEntity(
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