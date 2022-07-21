package com.example.data.repository

import com.example.data.entity.home.ArticleEntityMapper
import com.example.data.source.local.room.AppDatabase
import com.example.data.source.local.room.dao.article.ArticleDao
import com.example.data.source.local.room.entity.home.ArticleData
import com.example.data.source.local.room.entity.home.ArticleDataMapper
import com.example.data.util.ArticleDataConverter
import com.example.domain.model.home.Article
import com.example.domain.model.home.room.entity.ArticleDB
import com.example.domain.repository.IRoomDBRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class DBRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val articleDao: ArticleDao,
) : IRoomDBRepository {
    override fun addArticle(articles: List<Article>?) :Single<LongArray?> {
        val article = ArticleEntityMapper.mapper.fromDomainList(articles)
        val articlesDB = ArrayList<ArticleData>()
        article.forEach {
            val res = ArticleDataConverter().convertFrom(it)
            res?.let { it1 -> articlesDB.add(it1) }
        }
        return Single.fromCallable {
            return@fromCallable articleDao.insertArticle(articlesDB)
        }
    }

    override fun getAllArticles(): Flowable<List<ArticleDB>?>? {
        val list =  articleDao.getAllArticle()?.map {
            ArticleDataMapper.mapper.fromDomainList(it)
        }
        return list
    }
    override fun deleteAllArticles() : Completable {
         return articleDao.deleteAll()
    }

}