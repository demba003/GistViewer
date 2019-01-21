package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single
import org.koin.standalone.KoinComponent

class GetGistsUseCase(private val network: Network) : KoinComponent {
    fun getGists(): Single<List<Gist>> {
        return network.getBackend().getGists()
    }
}