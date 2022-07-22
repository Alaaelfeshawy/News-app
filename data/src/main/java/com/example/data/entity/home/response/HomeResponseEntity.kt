package com.example.data.entity.home.response

import com.example.data.entity.home.ArticleEntity
import com.example.domain.model.home.response.HomeResponse
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

data class HomeResponseEntity (
    var status :String ? =null,
    var source : String? = null,
    var sortBy : String ?= null,
    var articles: List<ArticleEntity> ? =null
)

@Mapper
interface HomeResponseEntityMapper {
    fun fromDomain(domain: HomeResponse?): HomeResponseEntity
    fun toDomain(data: HomeResponseEntity?): HomeResponse

    companion object {
        var mapper: HomeResponseEntityMapper =
            Mappers.getMapper(HomeResponseEntityMapper::class.java)
    }
}