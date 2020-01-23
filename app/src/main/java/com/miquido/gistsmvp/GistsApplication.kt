package com.miquido.gistsmvp

import android.app.Application
import com.miquido.gistsmvp.koin.androidModule
import com.miquido.gistsmvp.koin.networkModule
import com.miquido.gistsmvp.koin.presenterModule
import com.miquido.gistsmvp.koin.dataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GistsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GistsApplication)
            modules(listOf(androidModule, networkModule, presenterModule, dataSourceModule))
        }
    }
}