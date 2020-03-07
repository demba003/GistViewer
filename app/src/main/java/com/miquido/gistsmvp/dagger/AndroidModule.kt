package com.miquido.gistsmvp.dagger

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {

    @Provides
    fun provideGlide(context: Context): RequestManager = Glide.with(context)

}