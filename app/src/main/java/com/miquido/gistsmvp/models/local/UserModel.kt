package com.miquido.gistsmvp.models.local

import com.miquido.gistsmvp.models.db.UserEntity
import com.miquido.gistsmvp.models.network.User

data class UserModel(
    val id: Int,
    val login: String,
    val name: String?,
    val reposCount: Int,
    val followersCount: Int,
    val profileUrl: String,
    val avatarUrl: String
)

fun User.toLocalModel() =
    UserModel(hashCode(), login, name, public_repos, followers, html_url, avatar_url)

fun User.toDbModel() =
    UserEntity(hashCode(), login, name, public_repos, followers, html_url, avatar_url)

fun UserEntity.toLocalModel() =
    UserModel(id, login, name, reposCount, followersCount, profileUrl, avatarUrl)