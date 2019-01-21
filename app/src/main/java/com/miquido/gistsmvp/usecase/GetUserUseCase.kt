package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.network.Network
import io.reactivex.Single
import org.koin.standalone.KoinComponent

class GetUserUseCase(private val network: Network) : KoinComponent {
    fun getUser(name: String): Single<User> {
        return network.getBackend().getUser(name)
    }
}