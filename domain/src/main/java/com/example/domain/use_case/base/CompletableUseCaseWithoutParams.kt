package com.example.domain.use_case.base
import io.reactivex.Completable

abstract class CompletableUseCaseWithoutParams(
    private val schedulerProvider: SchedulerProvider
) : UseCase() {
    protected abstract fun buildUseCaseSingle(): Completable
    fun execute(completableUseCaseCallback: CompletableUseCaseCallback) {
        disposeLast()
        disposable = buildUseCaseSingle()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.mainThread)
            .subscribe({ completableUseCaseCallback.onSuccess() }) { throwable: Throwable ->
                completableUseCaseCallback.onError(
                    throwable
                )
            }

        disposable?.let {
            compositeDisposable.add(it)
        }
    }
}