package com.example.newsapp.model.home

import android.os.Parcel
import android.os.Parcelable
import com.example.domain.model.home.Article
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

data class ArticleModel (
    var id : Long? =null,
    var author: String?= null,
    var title: String? = null,
    var description : String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String?  = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleModel> {
        override fun createFromParcel(parcel: Parcel): ArticleModel {
            return ArticleModel(parcel)
        }

        override fun newArray(size: Int): Array<ArticleModel?> {
            return arrayOfNulls(size)
        }
    }
}

@Mapper
interface ArticleMapper {
    fun toDomainList(model: List<ArticleModel>?): List<Article>?
    fun fromDomainList(model: List<Article>?): List<ArticleModel>?

    companion object {
        var mapper: ArticleMapper =
            Mappers.getMapper(ArticleMapper::class.java)
    }
}