package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.local.toLocalModel
import com.miquido.gistsmvp.network.GistAPI

class GetGistDetailsUseCase(private val api: GistAPI) {
    suspend fun getGist(id: String) = api.getGist(id).toLocalModel()
}