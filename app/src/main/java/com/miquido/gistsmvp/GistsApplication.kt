package com.miquido.gistsmvp

import android.app.Application
import com.miquido.gistmvp.data.repository.repositoryModule
import com.miquido.gistmvp.usecase.useCaseModule
import com.miquido.gistsmvp.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GistsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GistsApplication)
            modules(listOf(androidModule, networkModule, viewModelModule, useCaseModule, repositoryModule))
        }
    }
}