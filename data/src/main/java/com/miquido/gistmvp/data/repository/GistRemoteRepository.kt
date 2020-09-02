package com.miquido.gistmvp.data.repository

import com.miquido.gistmvp.data.datasource.GistDataSource
import com.miquido.gistmvp.data.mapper.toLocalEntryModel
import com.miquido.gistmvp.data.mapper.toLocalModel

class GistRemoteRepository(private val gistDataSource: GistDataSource) {
    suspend fun getGists() = gistDataSource.getGists().toLocalEntryModel()

    suspend fun getGist(id: String) = gistDataSource.getGist(id).toLocalModel()

    suspend fun getUser(username: String) = gistDataSource.getUser(username).toLocalModel()
}
