package com.miquido.gistmvp.data.mapper

import com.miquido.gistmvp.data.network.Gist
import com.miquido.gistmvp.data.network.GistFile
import com.miquido.gistsmvp.domain.GistDetailsModel
import com.miquido.gistsmvp.domain.GistFileModel

fun GistFile.toLocalModel() =
    GistFileModel(hashCode(), filename, content)

fun Gist.toLocalModel() =
    GistDetailsModel(id, description, owner.login, files.values.first().toLocalModel())
