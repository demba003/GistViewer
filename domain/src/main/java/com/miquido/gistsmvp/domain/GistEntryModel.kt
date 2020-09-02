package com.miquido.gistsmvp.domain

data class GistEntryModel(
    val id: String,
    val ownerLogin: String,
    val ownerAvatarUrl: String?,
    val description: String?
)
