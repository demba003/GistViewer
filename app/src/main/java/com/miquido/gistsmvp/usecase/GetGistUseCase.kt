package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single
import org.koin.standalone.KoinComponent

class GetGistUseCase(private val network: Network) : KoinComponent {
    fun getGist(id: String): Single<Gist> {
        return network.getBackend().getGist(id)
    }
}