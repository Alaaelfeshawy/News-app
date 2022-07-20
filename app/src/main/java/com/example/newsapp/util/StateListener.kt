package com.example.newsapp.util

import javax.inject.Inject

class StateListener @Inject constructor() {
    val errorMessage: SingleLiveEvent<Int> = SingleLiveEvent()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun setErrorMessage(errorMessage: Int?) {
        this.errorMessage.value = errorMessage
    }
}