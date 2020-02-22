package com.miquido.gistsmvp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miquido.gistsmvp.models.db.GistDetailsEntity
import io.reactivex.Single

@Dao
interface GistDetailsDao {
    @Query("SELECT * FROM gist")
    fun getAll(): Single<List<GistDetailsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(user: GistDetailsEntity)}