package com.miquido.gistsmvp.dagger

import com.miquido.gistsmvp.BuildConfig
import com.miquido.gistsmvp.network.GistAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    fun provideGistApi(retrofit: Retrofit): GistAPI = retrofit.create(GistAPI::class.java)

}
