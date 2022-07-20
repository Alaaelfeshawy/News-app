package com.example.data.factory

import com.example.data.entity.home.response.HomeResponseEntity
import com.example.domain.model.home.response.HomeResponse

object HomeResponseEntityFactory {
    const val NUM_ATTRIBUTES=4

    fun generateDataForHomeResponseEntity(): HomeResponseEntity {
        return HomeResponseEntity(
            "status",
            "source",
            "sortBy",
            listOf(ArticleEntityFactory.generateDataForArticleEntity())
        )
    }

    fun generateDataForHomeResponseDomain(): HomeResponse {
        return HomeResponse(
            "status",
            "source",
            "sortBy",
            listOf(ArticleEntityFactory.generateDataForArticleDomain())
        )
    }
}