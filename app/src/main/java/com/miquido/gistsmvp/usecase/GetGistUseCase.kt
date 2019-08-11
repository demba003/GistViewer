package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class GetGistUseCase(private val api: GistAPI) {
    fun getGist(id: String): Single<Gist> {
        return api.getGist(id)
    }
}