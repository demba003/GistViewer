package com.miquido.gistsmvp.models.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GistFile(
    val filename: String,
    val content: String?
) : Parcelable