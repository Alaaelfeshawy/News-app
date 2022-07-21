package com.example.data.factory

import com.example.data.entity.home.ArticleEntity
import com.example.domain.model.home.Article

object ArticleEntityFactory {
    const val NUM_ATTRIBUTES=6

    fun generateDataForArticleEntity(): ArticleEntity {
        return ArticleEntity(
            1,
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
            1,
            "author",
            "title",
            "des",
            "url",
            "urlToImage",
            "publishedAt",
        )
    }
}