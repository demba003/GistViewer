package com.miquido.gistsmvp.koin

import com.miquido.gistsmvp.screen.gistdetails.DetailsContract
import com.miquido.gistsmvp.screen.gistdetails.DetailsPresenter
import com.miquido.gistsmvp.screen.gistlist.ListContract
import com.miquido.gistsmvp.screen.gistlist.ListPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory<ListContract.Presenter> { ListPresenter(get(), get()) }
    factory<DetailsContract.Presenter> { DetailsPresenter(get(), get(), get()) }
}
