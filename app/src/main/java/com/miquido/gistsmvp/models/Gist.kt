package com.miquido.gistsmvp.models

import android.os.Parcel
import android.os.Parcelable

data class Gist(val id: String, val owner: Owner, val description: String, val files: Map<String, GistFile>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Owner::class.java.classLoader),
        parcel.readString(),
        parcel.readMap()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(owner, flags)
        parcel.writeString(description)
        parcel.writeMap(files)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Gist> {
        override fun createFromParcel(parcel: Parcel): Gist {
            return Gist(parcel)
        }

        override fun newArray(size: Int): Array<Gist?> {
            return arrayOfNulls(size)
        }
    }
}

private fun Parcel.readMap(): Map<String, GistFile> {
    val map = mutableMapOf<String, GistFile>()
    this.readMap(map, GistFile::class.java.classLoader)
    return map
}