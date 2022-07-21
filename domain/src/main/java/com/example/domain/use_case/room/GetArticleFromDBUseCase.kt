package com.example.domain.use_case.room

import com.example.domain.model.home.room.entity.ArticleDB
import com.example.domain.repository.IRoomDBRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetArticleFromDBUseCase @Inject constructor(private val dbRepository: IRoomDBRepository) {
    fun getAllArticles() : Flowable<List<ArticleDB>?>?{
        return dbRepository.getAllArticles()
    }
}