package com.example.domain.use_case.base

import io.reactivex.Scheduler

interface SchedulerProvider {
    val mainThread: Scheduler
    val io: Scheduler
    val newThread: Scheduler
}