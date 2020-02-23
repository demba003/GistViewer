package com.miquido.gistsmvp.models.local

import com.miquido.gistsmvp.models.db.GistDetailsEntity
import com.miquido.gistsmvp.models.network.Gist

data class GistDetailsModel(
    val id: String,
    val description: String,
    val ownerLogin: String,
    val file: GistFileModel
)

fun Gist.toLocalModel() =
    GistDetailsModel(id, description, owner.login, files.values.first().toLocalModel())

fun Gist.toDbModel() =
    GistDetailsEntity(
        id,
        description,
        owner.login,
        files.values.first().toLocalModel().id,
        files.values.first().toLocalModel().filename,
        files.values.first().toLocalModel().content
    )

fun GistDetailsEntity.toLocalModel() =
    GistDetailsModel(id, description, ownerLogin, GistFileModel(fileId, filename, content))
