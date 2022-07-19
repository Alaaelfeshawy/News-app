package com.example.domain.use_case.base

import io.reactivex.Observable

abstract class ObservableUseCase<T, Params>(
    private val schedulerProvider: SchedulerProvider
) : UseCase() {
    protected abstract fun buildUseCaseSingle(params: Params): Observable<T>

    fun execute(
        params: Params,
        params2: Params,
        observableUseCaseCallback: ObservableUseCaseCallback<T>,
        disposeLast: Boolean = true
    ){
        if (disposeLast) {
            disposeLast()
        }

        disposable = Observable.zip(buildUseCaseSingle(params), buildUseCaseSingle(params2)) { res1, res2 ->
            val combineList = mutableListOf<T>()
            combineList.add(res1)
            combineList.add(res2)
            combineList
        }.subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.mainThread)
            .doAfterTerminate {observableUseCaseCallback.onFinished()}
            .subscribe({t  -> observableUseCaseCallback.onSuccess(t)})
            { throwable: Throwable ->
                observableUseCaseCallback.onError(
                    throwable
                )
            }
        disposable?.let {
            compositeDisposable.add(it)
        }
    }

    }
