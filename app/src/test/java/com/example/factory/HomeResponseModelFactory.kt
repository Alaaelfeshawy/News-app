package com.example.factory

import com.example.domain.model.home.response.HomeResponse
import com.example.newsapp.model.home.response.HomeResponseModel

object HomeResponseModelFactory {
    const val NUM_ATTRIBUTES=4

    fun generateDataForHomeResponseModel(): HomeResponseModel {
        return HomeResponseModel(
            "status",
            "source",
            "sortBy",
            listOf(ArticleModelFactory.generateDataForArticleModel())
        )
    }

    fun generateDataForHomeResponseDomain(): HomeResponse {
        return HomeResponse(
            "status",
            "source",
            "sortBy",
            listOf(ArticleModelFactory.generateDataForArticleDomain())
        )
    }
}