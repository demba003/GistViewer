package com.miquido.gistmvp.data.mapper

import com.miquido.gistmvp.data.network.User
import com.miquido.gistsmvp.domain.UserModel

fun User.toLocalModel() =
    UserModel(hashCode(), login, name, public_repos, followers, html_url, avatar_url)
