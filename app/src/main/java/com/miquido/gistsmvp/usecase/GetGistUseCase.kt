package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single

class GetGistUseCase {
    fun get(id: String): Single<FileGist> {
        return Network.getBackend()
            .getGist(id)
    }
}