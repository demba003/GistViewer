package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class GetUserUseCase : KoinComponent {
    private val network: Network by inject()

    fun get(name: String): Single<User> {
        return network.getBackend()
            .getUser(name)
    }
}