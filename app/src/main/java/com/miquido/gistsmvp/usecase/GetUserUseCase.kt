package com.miquido.gistsmvp.usecase

import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class GetUserUseCase(private val api: GistAPI) {
    fun getUser(name: String): Single<User> {
        return api.getUser(name)
    }
}