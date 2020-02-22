package com.miquido.gistsmvp.models.network

data class Gist(
    val id: String,
    val owner: Owner,
    val description: String,
    val files: Map<String, GistFile>
)
