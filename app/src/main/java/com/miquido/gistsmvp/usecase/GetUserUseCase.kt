package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.local.toLocalModel
import com.miquido.gistsmvp.network.GistAPI

class GetUserUseCase(private val api: GistAPI) {
    suspend fun getUser(name: String) = api.getUser(name).toLocalModel()
}
