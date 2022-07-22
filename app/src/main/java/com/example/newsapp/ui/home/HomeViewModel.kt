package com.example.newsapp.ui.home

import com.example.domain.model.home.response.HomeResponse
import com.example.domain.use_case.base.ObservableUseCaseCallback
import com.example.domain.use_case.home.GetHomeDataUseCase
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.model.home.response.HomeResponseModelMapper
import com.example.newsapp.ui.base.BaseViewModel
import com.example.newsapp.util.ErrorMessageUtils
import com.example.newsapp.util.SingleLiveEvent
import com.example.newsapp.util.StateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    stateListener: StateListener,
    errorMessageUtils: ErrorMessageUtils,
    private val getHomeDataUseCase: GetHomeDataUseCase,
) :  BaseViewModel(
    stateListener,
    errorMessageUtils,
    getHomeDataUseCase,
) {
     val homeData: SingleLiveEvent<List<ArticleModel>> = SingleLiveEvent()
     val error: SingleLiveEvent<String> = SingleLiveEvent()
     val noInternet: SingleLiveEvent<Boolean> = SingleLiveEvent()
     var filteredList = SingleLiveEvent<Pair<ArrayList<ArticleModel>,String>>()

    fun getHomeData(){
        stateListener.loading.value = true
        getHomeDataUseCase.execute(
            GetHomeDataUseCase.Params("the-next-web"),
            GetHomeDataUseCase.Params("associated-press"),
            object : ObservableUseCaseCallback<HomeResponse?> {
                override fun onSuccess(t: List<HomeResponse?>) {
                    val list = ArrayList<ArticleModel>()
                    t.forEach { homeResponse ->
                        val res = HomeResponseModelMapper.mapper.fromDomain(homeResponse)
                        res.articles?.let { list.addAll(it) }
                    }
                    homeData.value = list
                    noInternet.value= false
                }
                override fun onError(throwable: Throwable) {
                    stateListener.loading.value = false
                    errorMessageUtils.getErrorMessage(
                        throwable,
                        stateListener,
                        error,
                        noInternet
                    )
                }

                override fun onFinished() {
                    stateListener.loading.value = false
                }

            },
        )



    }

    fun search(query:String){
        if(!homeData.value?.toList().isNullOrEmpty()){
            val list = homeData.value?.toList()?.filter {
                it.title?.contains(query,true) == true
            } as ArrayList<ArticleModel>
            val pair = Pair(list,query)
            filteredList.value = pair
        }
    }
}