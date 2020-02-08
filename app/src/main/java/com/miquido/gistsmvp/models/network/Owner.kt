package com.miquido.gistsmvp.models.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val login: String,
    val avatar_url: String
) : Parcelable