package com.miquido.gistsmvp.koin

import com.miquido.gistsmvp.usecase.GetGistDetailsUseCase
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetUserUseCase(get()) }
    single { GetGistDetailsUseCase(get()) }
    single { GetGistsUseCase(get()) }
}
