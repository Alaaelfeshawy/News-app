package com.example.data.util

import com.example.data.entity.home.ArticleEntity
import com.example.data.source.local.room.entity.home.ArticleData

class ArticleDataConverter {
    fun convertFrom(articleModel: ArticleEntity?): ArticleData? {
        if (articleModel == null) return null
        return ArticleData(
            null,
            articleModel.author,
            articleModel.title,
            articleModel.description,
            articleModel.url,
            articleModel.urlToImage,
            articleModel.publishedAt
        )

    }
    fun convertTo(articleModel: ArticleData?): ArticleEntity? {
        if (articleModel == null) return null
        return ArticleEntity(
            null,
            articleModel.author,
            articleModel.title,
            articleModel.description,
            articleModel.url,
            articleModel.urlToImage,
            articleModel.publishedAt
        )

    }
}