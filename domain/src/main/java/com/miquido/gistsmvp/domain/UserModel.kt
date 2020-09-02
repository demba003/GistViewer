package com.miquido.gistsmvp.domain

data class UserModel(
    val id: Int,
    val login: String,
    val name: String?,
    val reposCount: Int,
    val followersCount: Int,
    val profileUrl: String,
    val avatarUrl: String
)
