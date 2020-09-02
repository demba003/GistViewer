package com.miquido.gistmvp.data.mapper

import com.miquido.gistmvp.data.network.Gist
import com.miquido.gistsmvp.domain.GistEntryModel

fun Gist.toLocalEntryModel() =
    GistEntryModel(id, owner.login, owner.avatar_url, description)

fun List<Gist>.toLocalEntryModel() = map { it.toLocalEntryModel() }
