package com.miquido.gistsmvp.koin

import com.miquido.gistsmvp.repository.GistDetailsRepository
import com.miquido.gistsmvp.repository.GistsRepository
import com.miquido.gistsmvp.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(get(), get()) }
    single { GistDetailsRepository(get()) }
    single { GistsRepository(get()) }
}
