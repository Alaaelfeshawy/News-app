package com.example.data.entity.home

import com.example.domain.model.home.Article
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

data class ArticleEntity (
    var id : Long? =null,
    var author: String?= null,
    var title: String? = null,
    var description : String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String?  = null
)

@Mapper
interface ArticleEntityMapper {
    fun fromDomainList(domain: List<Article>?): List<ArticleEntity>
    fun toDomainList(domain: List<ArticleEntity>?): List<Article>
    fun toDomain(data: ArticleEntity?): Article

    companion object {
        var mapper: ArticleEntityMapper =
            Mappers.getMapper(ArticleEntityMapper::class.java)
    }
}