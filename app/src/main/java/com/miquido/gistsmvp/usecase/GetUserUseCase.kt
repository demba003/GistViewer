package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single

class GetUserUseCase(private val network: Network) {
    fun getUser(name: String): Single<User> {
        return network.getBackend().getUser(name)
    }
}