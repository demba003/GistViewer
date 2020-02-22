package com.miquido.gistsmvp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miquido.gistsmvp.models.db.GistDetailsEntity
import com.miquido.gistsmvp.models.db.UserEntity

@Database(entities = [UserEntity::class, GistDetailsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun gistDetailsDao(): GistDetailsDao
}
