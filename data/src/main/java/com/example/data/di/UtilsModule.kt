package com.example.data.di

import com.example.data.util.MoshiConverterUtil
import com.example.data.util.rx.AppSchedulerProvider
import com.example.domain.use_case.base.SchedulerProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UtilsModule {
    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun providerMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideMoshiConverterUtil(moshi: Moshi?): MoshiConverterUtil {
//        return MoshiConverterUtil(moshi!!)
//    }
}