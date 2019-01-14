package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class GetGistsUseCase : KoinComponent {
    private val network: Network by inject()

    fun get(): Single<List<Gist>> {
        return network.getBackend()
            .getGists()
    }
}