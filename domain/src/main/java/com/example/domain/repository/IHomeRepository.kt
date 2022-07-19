package com.example.domain.repository

import com.example.domain.model.home.response.HomeResponse
import io.reactivex.Observable
import io.reactivex.Single

interface IHomeRepository {
    fun homeData(source: String): Observable<HomeResponse?>
}