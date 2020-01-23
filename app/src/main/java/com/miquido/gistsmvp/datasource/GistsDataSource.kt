package com.miquido.gistsmvp.datasource

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class GistsDataSource(private val api: GistAPI) {
    fun getGists(): Single<List<Gist>> {
        return api.getGists()
    }
}