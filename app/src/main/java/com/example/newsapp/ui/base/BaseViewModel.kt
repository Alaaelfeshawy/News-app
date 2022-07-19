package com.example.newsapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.domain.use_case.base.UseCase
import com.example.newsapp.util.ErrorMessageUtils
import com.example.newsapp.util.NetworkUtils
import com.example.newsapp.util.StateListener

abstract class BaseViewModel(
    var stateListener: StateListener,
    val errorMessageUtils: ErrorMessageUtils,
    vararg useCase: UseCase?
) : ViewModel() {

    private var useCaseList: MutableList<UseCase?> = ArrayList()

    init {
        useCaseList.addAll(listOf(*useCase))
    }

    override fun onCleared() {
        super.onCleared()
        for (useCase in useCaseList) {
            useCase?.dispose()
        }
    }
}