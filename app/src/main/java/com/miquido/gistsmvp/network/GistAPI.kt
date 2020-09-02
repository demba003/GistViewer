package com.miquido.gistsmvp.network


import com.miquido.gistmvp.data.datasource.GistDataSource
import com.miquido.gistmvp.data.network.Gist
import com.miquido.gistmvp.data.network.User
import retrofit2.http.GET
import retrofit2.http.Path

const val GISTS = "/gists"
const val USERS = "/users"

interface GistAPI : GistDataSource {
    @GET(GISTS)
    override suspend fun getGists(): List<Gist>

    @GET("$GISTS/{id}")
    override suspend fun getGist(@Path("id") id: String): Gist

    @GET("$USERS/{username}")
    override suspend fun getUser(@Path("username") username: String): User
}
