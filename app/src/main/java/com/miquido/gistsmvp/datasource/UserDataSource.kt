package com.miquido.gistsmvp.datasource

import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single

class UserDataSource(private val api: GistAPI) {
    fun getUser(name: String): Single<User> {
        return api.getUser(name)
    }
}