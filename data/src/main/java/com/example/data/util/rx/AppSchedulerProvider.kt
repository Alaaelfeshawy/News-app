package com.example.data.util.rx

import com.example.domain.use_case.base.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {
    override val mainThread: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val io: Scheduler
        get() = Schedulers.io()
    override val newThread: Scheduler
        get() = Schedulers.newThread()
}