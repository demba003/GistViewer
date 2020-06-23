package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.local.toLocalEntryModel
import com.miquido.gistsmvp.network.GistAPI

class GetGistsUseCase(private val api: GistAPI) {
    suspend fun getGists() = api.getGists().toLocalEntryModel()
}
