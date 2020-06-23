package com.miquido.gistsmvp.koin

import com.miquido.gistsmvp.screen.gistdetails.DetailsViewModel
import com.miquido.gistsmvp.screen.gistlist.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailsViewModel(get(), get()) }
    viewModel { ListViewModel(get()) }
}