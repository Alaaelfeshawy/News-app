package com.example.data.source.local.room.dao.article

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.source.local.room.AppDatabase
import com.example.data.source.local.room.dao.BaseDao
import com.example.data.source.local.room.entity.home.ArticleData
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
abstract class ArticleDao(private val appDatabase: AppDatabase) : BaseDao<ArticleData?> {

    @Transaction
    @Query("SELECT * FROM articles")
    abstract fun getAllArticle(): Flowable<List<ArticleData>>?

    @Transaction
    open fun insertArticle (articles: List<ArticleData>?) : LongArray?{
        if (articles != null) {
            return appDatabase.articleDao().insert(articles)
        }
        return  null
    }

    @Query("DELETE FROM articles")
    abstract fun deleteAll() : Completable

}