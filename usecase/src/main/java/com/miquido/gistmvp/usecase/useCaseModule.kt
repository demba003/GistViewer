package com.miquido.gistmvp.usecase

import org.koin.dsl.module

val useCaseModule = module {
    single { GetUserUseCase(get()) }
    single { GetGistDetailsUseCase(get()) }
    single { GetGistsUseCase(get()) }
}
