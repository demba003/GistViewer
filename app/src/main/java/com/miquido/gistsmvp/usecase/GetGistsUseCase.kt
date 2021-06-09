package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.local.toLocalEntryModel
import com.miquido.gistsmvp.network.GistAPI
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetGistsUseCase(private val api: GistAPI) {
    fun getGists() = flow { emit(api.getGists()) }
        .map { it.toLocalEntryModel() }
}
