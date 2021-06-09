package com.miquido.gistsmvp

import android.app.Application
import com.miquido.gistsmvp.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GistsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@GistsApplication)
            modules(listOf(androidModule, networkModule, viewModelModule, useCaseModule))
        }
    }
}
