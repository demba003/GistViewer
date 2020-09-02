package com.miquido.gistmvp.data.network

data class Gist(
    val id: String,
    val owner: Owner,
    val description: String,
    val files: Map<String, GistFile>
)
