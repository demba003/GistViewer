package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single

class GetGistsUseCase(private val network: Network) {
    fun getGists(): Single<List<Gist>> {
        return network.getBackend().getGists()
    }
}