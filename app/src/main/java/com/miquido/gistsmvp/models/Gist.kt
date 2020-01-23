package com.miquido.gistsmvp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gist(
    val id: String,
    val owner: Owner,
    val description: String,
    val files: Map<String, GistFile>
) : Parcelable
