package com.example.domain.repository

import com.example.domain.model.home.Article
import com.example.domain.model.home.room.entity.ArticleDB
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IRoomDBRepository {
    fun getAllArticles(): Flowable<List<ArticleDB>?>?
    fun addArticle(articles: List<Article>?) : Single<LongArray?>
    fun deleteAllArticles() : Completable
}