package com.miquido.gistsmvp.network

import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GistAPI {
    @GET("/gists")
    fun getGists(): Single<List<Gist>>

    @GET("/gists/{id}")
    fun getGist(@Path("id") id: String): Single<FileGist>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Single<User>
}