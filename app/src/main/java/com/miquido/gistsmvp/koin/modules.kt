package com.miquido.gistsmvp.koin

import com.bumptech.glide.Glide
import com.miquido.gistsmvp.network.Network
import com.miquido.gistsmvp.schedulers.DeviceSchedulerProvider
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.screen.gistdetails.DetailsContract
import com.miquido.gistsmvp.screen.gistdetails.DetailsPresenter
import com.miquido.gistsmvp.screen.gistlist.ListContract
import com.miquido.gistsmvp.screen.gistlist.ListPresenter
import com.miquido.gistsmvp.usecase.GetGistUseCase
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val modules = module {
    single { Network() }
    single<SchedulerProvider> { DeviceSchedulerProvider() }
    single { GetUserUseCase(get()) }
    single { GetGistUseCase(get()) }
    single { GetGistsUseCase(get()) }
    factory<ListContract.Presenter> { ListPresenter(get(), get()) }
    factory<DetailsContract.Presenter> { DetailsPresenter(get(), get(), get()) }
    single { Glide.with(androidContext()) }
}