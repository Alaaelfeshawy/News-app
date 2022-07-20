package com.example.data.source.remote


import com.example.data.entity.home.response.HomeResponseEntity
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface HomeApi {
    @GET("articles")
    fun getHomeData(@Query("source") source: String? , @Query("apiKey") apiKey : String): Observable<HomeResponseEntity>
}