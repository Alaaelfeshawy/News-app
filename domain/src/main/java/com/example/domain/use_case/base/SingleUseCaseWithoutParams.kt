package com.example.domain.use_case.base
import io.reactivex.Single

abstract class SingleUseCaseWithoutParams<T>(
    private val schedulerProvider: SchedulerProvider
) : UseCase() {
    protected abstract fun buildUseCaseSingle(): Single<T>
    fun execute(
        singleUseCaseCallback: SingleUseCaseCallback<T>,
        disposeLast: Boolean = true
    ) {
        if (disposeLast) {
            disposeLast()
        }
        disposable = buildUseCaseSingle()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.mainThread)
            .doAfterTerminate { singleUseCaseCallback.onFinished() }
            .subscribe({ t: T -> singleUseCaseCallback.onSuccess(t) }) { throwable: Throwable ->
                singleUseCaseCallback.onError(
                    throwable
                )
            }
        disposable?.let { compositeDisposable.add(it) }
    }
}