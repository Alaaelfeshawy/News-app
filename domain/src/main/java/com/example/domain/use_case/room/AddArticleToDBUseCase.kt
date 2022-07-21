package com.example.domain.use_case.room

import com.example.domain.model.home.Article
import com.example.domain.repository.IRoomDBRepository
import com.example.domain.use_case.base.SchedulerProvider
import com.example.domain.use_case.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class AddArticleToDBUseCase @Inject constructor(
    private val dbRepository: IRoomDBRepository,
    private val schedulerProvider: SchedulerProvider
) :
    SingleUseCase<LongArray?, AddArticleToDBUseCase.Params>(schedulerProvider) {

    override fun buildUseCaseSingle(params: Params): Single<LongArray?> {
       return dbRepository.addArticle(params.articles)
    }
    class Params(
        var articles: List<Article>,
    )


}