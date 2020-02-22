package com.miquido.gistsmvp.models.local

import com.miquido.gistsmvp.models.db.GistDetailsEntity
import com.miquido.gistsmvp.models.network.Gist

data class GistEntryModel(
    val id: String,
    val ownerLogin: String,
    val ownerAvatarUrl: String?,
    val description: String
)

fun Gist.toLocalEntryModel() =
    GistEntryModel(id, owner.login, owner.avatar_url, description)

fun List<Gist>.toLocalEntryModel() = map { it.toLocalEntryModel() }

fun GistDetailsEntity.toLocalEntryModel() =
    GistEntryModel(id, "TODO", "TODO", description)
