package com.miquido.gistsmvp.koin

import com.bumptech.glide.Glide
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single { Glide.with(androidContext()) }
}
