package com.example.newsapp.model.home.response

import com.example.domain.model.home.response.HomeResponse
import com.example.newsapp.model.home.ArticleModel
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

data class HomeResponseModel (
    var status :String ? =null,
    var source : String? = null,
    var sortBy : String ?= null,
    var articles: List<ArticleModel> ? =null
)

@Mapper
interface HomeResponseModelMapper {
    fun fromDomain(domain: HomeResponse?): HomeResponseModel
    fun fromListDomain(domain: List<HomeResponse?>?): List<HomeResponseModel>
    fun toDomain(data: HomeResponseModel?): HomeResponse

    companion object {
        var mapper: HomeResponseModelMapper =
            Mappers.getMapper(HomeResponseModelMapper::class.java)
    }
}