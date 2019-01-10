package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single

class GetUserUseCase {
    fun get(name: String): Single<User> {
        return Network.getBackend()
            .getUser(name)
    }
}