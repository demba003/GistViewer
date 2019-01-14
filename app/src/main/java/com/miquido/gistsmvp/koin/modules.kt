package com.miquido.gistsmvp.koin

import com.miquido.gistsmvp.network.Network
import com.miquido.gistsmvp.schedulers.DeviceSchedulerProvider
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistUseCase
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import org.koin.dsl.module.module

val modules = module {
    single { Network() }
    single<SchedulerProvider> { DeviceSchedulerProvider() }
    single { GetUserUseCase() }
    single { GetGistUseCase() }
    single { GetGistsUseCase() }
}