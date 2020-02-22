package com.miquido.gistsmvp.koin

import androidx.room.Room
import com.miquido.gistsmvp.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database").build()
    }
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().gistDetailsDao() }
}
