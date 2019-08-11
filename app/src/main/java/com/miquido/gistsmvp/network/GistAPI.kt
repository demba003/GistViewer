package com.miquido.gistsmvp.network

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

const val GISTS = "/gists"
const val USERS = "/users"

interface GistAPI {
    @GET(GISTS)
    fun getGists(): Single<List<Gist>>

    @GET("$GISTS/{id}")
    fun getGist(@Path("id") id: String): Single<Gist>

    @GET("$USERS/{username}")
    fun getUser(@Path("username") username: String): Single<User>
}
