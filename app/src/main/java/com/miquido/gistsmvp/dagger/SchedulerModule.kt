package com.miquido.gistsmvp.dagger

import com.miquido.gistsmvp.schedulers.DeviceSchedulerProvider
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import dagger.Binds
import dagger.Module

@Module
interface SchedulerModule {

    @Binds
    fun provideSchedulers(schedulers: DeviceSchedulerProvider): SchedulerProvider

}
