package com.miquido.gistsmvp

import android.app.Application
import com.miquido.gistsmvp.koin.modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GistsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GistsApplication)
            modules(modules)
        }
    }
}