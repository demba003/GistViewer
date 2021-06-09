package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.local.toLocalModel
import com.miquido.gistsmvp.network.GistAPI
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetGistDetailsUseCase(private val api: GistAPI) {
    fun getGist(id: String) = flow { emit(api.getGist(id)) }
        .map { it.toLocalModel() }
}
