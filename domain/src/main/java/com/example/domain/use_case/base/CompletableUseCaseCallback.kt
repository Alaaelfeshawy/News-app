package com.example.domain.use_case.base
interface CompletableUseCaseCallback {
    fun onSuccess()
    fun onError(throwable: Throwable)
}