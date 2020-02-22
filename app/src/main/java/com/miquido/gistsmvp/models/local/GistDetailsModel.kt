package com.miquido.gistsmvp.models.local

import com.miquido.gistsmvp.models.network.Gist

data class GistDetailsModel(
    val id: String,
    val description: String,
    val file: GistFileModel?
)


fun Gist.toLocalModel() =
    GistDetailsModel(id, description, files.values.firstOrNull()?.toLocalModel())
