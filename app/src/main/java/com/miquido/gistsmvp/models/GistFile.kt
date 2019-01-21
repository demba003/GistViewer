package com.miquido.gistsmvp.models

import android.os.Parcel
import android.os.Parcelable

data class GistFile(val filename: String, val content: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(filename)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GistFile> {
        override fun createFromParcel(parcel: Parcel): GistFile {
            return GistFile(parcel)
        }

        override fun newArray(size: Int): Array<GistFile?> {
            return arrayOfNulls(size)
        }
    }
}