package com.miquido.gistmvp.data.datasource

import com.miquido.gistmvp.data.network.Gist
import com.miquido.gistmvp.data.network.User

interface GistDataSource {
    suspend fun getGists(): List<Gist>

    suspend fun getGist(id: String): Gist

    suspend fun getUser(username: String): User
}
