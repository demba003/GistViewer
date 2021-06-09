package com.miquido.gistsmvp.network

import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.models.network.User
import retrofit2.http.GET
import retrofit2.http.Path

const val GISTS = "/gists"
const val USERS = "/users"

interface GistAPI {
    @GET(GISTS)
    suspend fun getGists(): List<Gist>

    @GET("$GISTS/{id}")
    suspend fun getGist(@Path("id") id: String): Gist

    @GET("$USERS/{username}")
    suspend fun getUser(@Path("username") username: String): User
}
