package com.miquido.gistsmvp.datasource

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class GistDataSource(private val api: GistAPI) {
    fun getGist(id: String): Single<Gist> {
        return api.getGist(id)
    }
}