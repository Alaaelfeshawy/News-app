package com.example.newsapp.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.domain.model.home.Article
import com.example.domain.model.home.response.HomeResponse
import com.example.domain.use_case.base.CompletableUseCaseCallback
import com.example.domain.use_case.base.ObservableUseCaseCallback
import com.example.domain.use_case.base.SingleUseCaseCallback
import com.example.domain.use_case.home.GetHomeDataUseCase
import com.example.domain.use_case.room.AddArticleToDBUseCase
import com.example.domain.use_case.room.DeleteArticleFromDBUseCase
import com.example.domain.use_case.room.GetArticleFromDBUseCase
import com.example.newsapp.model.home.ArticleMapper
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.model.home.response.HomeResponseModelMapper
import com.example.newsapp.model.room.entity.ArticleModelMapper
import com.example.newsapp.ui.base.BaseViewModel
import com.example.newsapp.util.ErrorMessageUtils
import com.example.newsapp.util.NetworkUtils
import com.example.newsapp.util.SingleLiveEvent
import com.example.newsapp.util.StateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class HomeViewModel  @Inject constructor(
    networkUtils: NetworkUtils,
    stateListener: StateListener,
    errorMessageUtils: ErrorMessageUtils,
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val addArticleToDBUseCase: AddArticleToDBUseCase,
    private val getArticlesFromDb: GetArticleFromDBUseCase,
    private val deleteArticleFromDBUseCase: DeleteArticleFromDBUseCase,
) :  BaseViewModel(
    stateListener,
    errorMessageUtils,
    getHomeDataUseCase,
    addArticleToDBUseCase,
) {
     val homeData: SingleLiveEvent<List<ArticleModel>> = SingleLiveEvent()
     val error: SingleLiveEvent<String> = SingleLiveEvent()
     val noInternet: SingleLiveEvent<Boolean> = SingleLiveEvent()
     val data: SingleLiveEvent<List<ArticleModel>> = SingleLiveEvent()

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

    fun addArticlesToDB(list: List<Article>){
        addArticleToDBUseCase.execute(AddArticleToDBUseCase.Params(list),
            object : SingleUseCaseCallback<LongArray?> {
                override fun onSuccess(t: LongArray?) {
                }
                override fun onError(throwable: Throwable) {
                    stateListener.loading.value = false
                    error.value = "failed to add to db"
                }
                override fun onFinished() {
                    stateListener.loading.value = false
                }

            },
        )
    }

    val getDataFromDb: LiveData<List<ArticleModel>>?
        get() = getArticlesFromDb.getAllArticles()?.map{ ArticleModelMapper.mapper.fromDomainList(it) }?.subscribeOn(
            Schedulers.io())?.toLiveData()

    fun deleteArticles(it : List<ArticleModel>){
        deleteArticleFromDBUseCase.execute(object : CompletableUseCaseCallback {
            override fun onSuccess()
            {
                val listToDb = ArticleMapper.mapper.toDomainList(it)
                listToDb?.let { it1 -> addArticlesToDB(it1) }
            }
            override fun onError(throwable: Throwable) {}
        })
    }

}