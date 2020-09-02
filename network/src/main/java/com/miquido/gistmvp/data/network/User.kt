package com.miquido.gistmvp.data.network

data class User(
    val login: String,
    val name: String?,
    val public_repos: Int,
    val followers: Int,
    val avatar_url: String,
    val html_url: String
)
