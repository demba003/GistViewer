package com.miquido.gistsmvp.repository

import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class GistsRepository(private val api: GistAPI) {
    fun getGists(): Single<List<Gist>> {
        return api.getGists()
    }
}