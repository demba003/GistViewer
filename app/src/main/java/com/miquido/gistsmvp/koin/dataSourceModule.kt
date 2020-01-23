package com.miquido.gistsmvp.koin

import com.miquido.gistsmvp.datasource.GistDataSource
import com.miquido.gistsmvp.datasource.GistsDataSource
import com.miquido.gistsmvp.datasource.UserDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { UserDataSource(get()) }
    single { GistDataSource(get()) }
    single { GistsDataSource(get()) }
}
