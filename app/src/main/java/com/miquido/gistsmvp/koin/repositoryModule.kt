package com.miquido.gistsmvp.koin

import com.miquido.gistsmvp.repository.GistRepository
import com.miquido.gistsmvp.repository.GistsRepository
import com.miquido.gistsmvp.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(get(), get()) }
    single { GistRepository(get()) }
    single { GistsRepository(get()) }
}
