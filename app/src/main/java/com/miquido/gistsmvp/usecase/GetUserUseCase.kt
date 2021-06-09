package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.local.toLocalModel
import com.miquido.gistsmvp.network.GistAPI
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetUserUseCase(private val api: GistAPI) {
    fun getUser(name: String) = flow { emit(api.getUser(name)) }
        .map { it.toLocalModel() }
}
