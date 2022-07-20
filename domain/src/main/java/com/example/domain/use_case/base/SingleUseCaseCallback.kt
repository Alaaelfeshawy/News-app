package com.example.domain.use_case.base

interface SingleUseCaseCallback<T> {
    fun onSuccess(t: T)
    fun onError(throwable: Throwable)
    fun onFinished()
}