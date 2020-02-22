package com.miquido.gistsmvp.repository

import com.miquido.gistsmvp.models.local.GistEntryModel
import com.miquido.gistsmvp.models.local.toLocalEntryModel
import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class GistsRepository(private val api: GistAPI) {
    fun getGists(): Single<List<GistEntryModel>> {
        return api.getGists()
            .map { it.toLocalEntryModel() }
    }
}