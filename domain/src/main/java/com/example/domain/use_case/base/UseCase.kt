package com.example.domain.use_case.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase {
    @JvmField
    protected var disposable: Disposable? = null
    @JvmField
    protected var compositeDisposable = CompositeDisposable()
    fun disposeLast() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable?.dispose()
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}