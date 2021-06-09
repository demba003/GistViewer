package com.miquido.gistsmvp.koin

import com.miquido.gistsmvp.BuildConfig
import com.miquido.gistsmvp.network.GistAPI
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<GistAPI> { get<Retrofit>().create(GistAPI::class.java) }
}
