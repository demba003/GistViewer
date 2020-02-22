package com.miquido.gistsmvp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miquido.gistsmvp.models.db.UserEntity
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): Single<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(user: UserEntity)
}
