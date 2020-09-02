package com.miquido.gistsmvp.domain

data class GistDetailsModel(
    val id: String,
    val description: String,
    val ownerLogin: String,
    val file: GistFileModel
)
