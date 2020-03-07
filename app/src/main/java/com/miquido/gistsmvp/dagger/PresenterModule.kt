package com.miquido.gistsmvp.dagger

import com.miquido.gistsmvp.screen.gistdetails.DetailsContract
import com.miquido.gistsmvp.screen.gistdetails.DetailsPresenter
import com.miquido.gistsmvp.screen.gistlist.ListContract
import com.miquido.gistsmvp.screen.gistlist.ListPresenter
import dagger.Binds
import dagger.Module

@Module
interface PresenterModule {

    @Binds
    fun provideDetailsPresenter(presenter: DetailsPresenter): DetailsContract.Presenter

    @Binds
    fun provideListPresenter(presenter: ListPresenter): ListContract.Presenter

}