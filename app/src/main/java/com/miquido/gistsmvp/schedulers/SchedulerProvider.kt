package com.miquido.gistsmvp.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface SchedulerProvider {
    fun io(): Scheduler
    fun main(): Scheduler
}

class DeviceSchedulerProvider @Inject constructor() : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.io()
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}

class TestSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun main(): Scheduler = Schedulers.trampoline()
}