package com.example.data.repository

import com.example.data.entity.home.response.HomeResponseEntityMapper
import com.example.data.source.remote.HomeApi
import com.example.domain.model.home.response.HomeResponse
import com.example.domain.repository.IHomeRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi
) : BaseRepository(), IHomeRepository {

    override fun homeData(source:String) : Observable<HomeResponse?> {
        return homeApi.getHomeData(source , apiToken).map {
            HomeResponseEntityMapper.mapper.toDomain(it)
        }
    }

}