package com.miquido.gistsmvp

import android.app.Application

class GistsApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}