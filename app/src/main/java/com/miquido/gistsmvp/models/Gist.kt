package com.miquido.gistsmvp.models

import java.io.Serializable

data class Gist(val id: String, val owner: Owner, val description: String) : Serializable