package com.miquido.gistsmvp.models.local

import com.miquido.gistsmvp.models.network.Gist

data class GistDetailsModel(
    val id: String,
    val description: String,
    val ownerLogin: String,
    val file: GistFileModel
)

fun Gist.toLocalModel() =
    GistDetailsModel(id, description, owner.login, files.values.first().toLocalModel())
