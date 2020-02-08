package com.miquido.gistsmvp.repository

import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class GistRepository(private val api: GistAPI) {
    fun getGist(id: String): Single<Gist> {
        return api.getGist(id)
    }
}