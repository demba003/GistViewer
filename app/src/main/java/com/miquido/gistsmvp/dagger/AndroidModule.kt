package com.miquido.gistsmvp.dagger

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class AndroidModule {

    @Provides
    fun provideGlide(@ApplicationContext context: Context): RequestManager = Glide.with(context)

}
