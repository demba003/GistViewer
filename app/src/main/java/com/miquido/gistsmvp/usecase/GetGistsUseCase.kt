package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single

class GetGistsUseCase {
    fun get(): Single<List<Gist>> {
        return Network.getBackend()
            .getGists()
    }
}