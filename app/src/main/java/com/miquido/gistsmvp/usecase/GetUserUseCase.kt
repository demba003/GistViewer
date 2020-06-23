package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.local.toLocalModel
import com.miquido.gistsmvp.network.GistAPI

class GetUserUseCase(private val api: GistAPI) {
    fun getUser(name: String) =
        api.getUser(name)
            .map { it.toLocalModel() }
}
