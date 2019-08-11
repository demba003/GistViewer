package com.miquido.gistsmvp.koin

import com.bumptech.glide.Glide
import com.miquido.gistsmvp.schedulers.DeviceSchedulerProvider
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single { Glide.with(androidContext()) }
    single<SchedulerProvider> { DeviceSchedulerProvider() }
}
