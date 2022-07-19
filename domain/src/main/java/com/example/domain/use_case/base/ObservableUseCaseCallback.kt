package com.example.domain.use_case.base

interface ObservableUseCaseCallback<T> {
    fun onSuccess(t: List<T>)
    fun onError(throwable: Throwable)
    fun onFinished()
}