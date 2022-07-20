package com.example.domain.use_case.home

import com.example.domain.model.home.response.HomeResponse
import com.example.domain.repository.IHomeRepository
import com.example.domain.use_case.base.ObservableUseCase
import com.example.domain.use_case.base.SchedulerProvider
import com.example.domain.use_case.base.SingleUseCase
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val homeRepository: IHomeRepository,
    private val schedulerProvider: SchedulerProvider
) :
    ObservableUseCase<HomeResponse?,GetHomeDataUseCase.Params >(schedulerProvider) {

    override fun buildUseCaseSingle(params: Params): Observable<HomeResponse?> {
       return homeRepository.homeData(params.source)
    }

    class Params(var source: String)



}