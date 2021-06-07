package com.miquido.gistsmvp.dagger

import com.miquido.gistsmvp.schedulers.DeviceSchedulerProvider
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface SchedulerModule {

    @Binds
    fun provideSchedulers(schedulers: DeviceSchedulerProvider): SchedulerProvider

}
