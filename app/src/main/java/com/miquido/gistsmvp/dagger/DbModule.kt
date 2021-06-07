package com.miquido.gistsmvp.dagger

import android.content.Context
import androidx.room.Room
import com.miquido.gistsmvp.db.AppDatabase
import com.miquido.gistsmvp.db.GistDetailsDao
import com.miquido.gistsmvp.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class DbModule {

    @Provides
    fun provideRoom(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun provideDetailsDao(database: AppDatabase): GistDetailsDao = database.gistDetailsDao()

}
