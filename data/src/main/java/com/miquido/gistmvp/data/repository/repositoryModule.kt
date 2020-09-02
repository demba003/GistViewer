package com.miquido.gistmvp.data.repository


import org.koin.dsl.module

val repositoryModule = module {
    single { GistRemoteRepository(get()) }
}
