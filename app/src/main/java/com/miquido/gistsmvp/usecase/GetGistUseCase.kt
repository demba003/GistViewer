package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class GetGistUseCase : KoinComponent {
    private val network: Network by inject()

    fun get(id: String): Single<FileGist> {
        return network.getBackend()
            .getGist(id)
    }
}