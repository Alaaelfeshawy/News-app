package com.example.newsapp.util

import com.example.newsapp.R
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorMessageUtils @Inject constructor(
    private val networkUtils: NetworkUtils,
) {


    fun getErrorMessage(
        throwable: Throwable,
        stateListener: StateListener,
        error:SingleLiveEvent<String>,
        noInternet :SingleLiveEvent<Boolean>
        ) {
        if (!networkUtils.isNetworkConnected) {
            noInternet.value = true
            stateListener.setErrorMessage(R.string.not_connected_to_internet)
        } else if (throwable is TimeoutException) {
            stateListener.setErrorMessage(R.string.not_connected_to_internet)
        } else {
            if (throwable is HttpException) {
                if (throwable.response()?.code() == 400 ||
                    throwable.response() ?.code() == 404 ||
                     throwable.response()
                        ?.code() == 422 || throwable.response()
                        ?.code() == 429
                ) {
                    try {
                        var errorBody: String? = null
                        try {
                            errorBody = throwable.response()
                                ?.errorBody()?.string()
                            val jObjError = JSONObject(errorBody ?: "")
                            val errors = jObjError.getString("message")
                            error.value = errors
                        } catch (e: IOException) {
                            e.printStackTrace()
                            stateListener.setErrorMessage(R.string.failed_to_load_data)
                        }
                    } catch (e: Exception) {
                        e.stackTrace
                        stateListener.setErrorMessage(R.string.failed_to_load_data)
                    }
                } else if (throwable.response()?.code() == 401) {
                    stateListener.setErrorMessage(R.string.not_authorize)
                } else {
                    stateListener.setErrorMessage(R.string.failed_to_load_data)
                }
            } else {
                stateListener.setErrorMessage(R.string.failed_to_load_data)
            }
        }
    }





}