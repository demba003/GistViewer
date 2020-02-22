package com.miquido.gistsmvp.models.local

import com.miquido.gistsmvp.models.network.GistFile

data class GistFileModel(
    val id: Int,
    val filename: String,
    val content: String?
)

fun GistFile.toLocalModel() =
    GistFileModel(hashCode(), filename, content)
