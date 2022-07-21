package com.example.domain.use_case.room

import com.example.domain.repository.IRoomDBRepository
import com.example.domain.use_case.base.CompletableUseCaseWithoutParams
import com.example.domain.use_case.base.SchedulerProvider
import io.reactivex.Completable
import javax.inject.Inject

class DeleteArticleFromDBUseCase @Inject constructor(private val dbRepository: IRoomDBRepository,
private val schedulerProvider: SchedulerProvider
) :CompletableUseCaseWithoutParams(schedulerProvider) {
        override fun buildUseCaseSingle(): Completable {
            return dbRepository.deleteAllArticles()
        }
}