package com.miquido.gistsmvp.repository

import com.miquido.gistsmvp.models.local.GistDetailsModel
import com.miquido.gistsmvp.models.local.toLocalModel
import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class GistDetailsRepository(private val api: GistAPI) {
    fun getGist(id: String): Single<GistDetailsModel> {
        return api.getGist(id)
            .map { it.toLocalModel() }
    }
}