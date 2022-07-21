package com.example.newsapp.model.room.entity

import com.example.domain.model.home.room.entity.ArticleDB
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

open class ArticleModel {
    var id : Long? =null
    var author: String?= null
    var title: String? = null
    var description : String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishedAt: String?  = null
}

@Mapper
interface ArticleModelMapper {
    fun fromDomainList(domain: List<ArticleDB>?): List<com.example.newsapp.model.home.ArticleModel>
    fun fromDomain(domain: ArticleDB?): ArticleModel
    fun toDomain(domain: ArticleModel?): ArticleDB

    companion object {
        var mapper: ArticleModelMapper =
            Mappers.getMapper(ArticleModelMapper::class.java)
    }
}