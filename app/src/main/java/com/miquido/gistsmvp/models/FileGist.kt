package com.miquido.gistsmvp.models

data class FileGist(val id: String, val owner: Owner, val description: String, val files: Map<String, File>)