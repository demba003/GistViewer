package com.miquido.gistsmvp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gist")
data class GistDetailsEntity(
    @PrimaryKey
    val id: String,
    val description: String,
    val fileId: Int,
    val filename: String,
    val content: String?
)
