package com.example.data.di

import com.example.data.repository.DBRepository
import com.example.data.repository.HomeRepository
import com.example.domain.repository.IHomeRepository
import com.example.domain.repository.IRoomDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun providerHomeRepository(homeRepository: HomeRepository): IHomeRepository {
        return homeRepository
    }
    @Provides
    @Singleton
    fun providerDBRepository(dBRepository: DBRepository): IRoomDBRepository {
        return dBRepository
    }
}