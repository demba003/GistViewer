package com.miquido.gistsmvp

import android.app.Application
import com.miquido.gistsmvp.koin.modules
import org.koin.android.ext.android.startKoin

class GistsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(modules))
    }
}