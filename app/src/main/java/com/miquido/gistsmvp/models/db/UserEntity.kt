package com.miquido.gistsmvp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val login: String,
    val name: String?,
    val reposCount: Int,
    val followersCount: Int,
    val profileUrl: String,
    val avatarUrl: String
)
