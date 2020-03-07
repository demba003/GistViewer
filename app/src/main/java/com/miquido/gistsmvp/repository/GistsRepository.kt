package com.miquido.gistsmvp.repository

import com.miquido.gistsmvp.db.GistDetailsDao
import com.miquido.gistsmvp.models.local.GistEntryModel
import com.miquido.gistsmvp.models.local.toLocalEntryModel
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Single
import javax.inject.Inject

class GistsRepository @Inject constructor(
    private val api: GistAPI,
    private val db: GistDetailsDao
) {
    fun getGists(): Single<List<GistEntryModel>> {
        return api.getGists()
            .map { it.toLocalEntryModel() }
            .onErrorResumeNext {
                db.getAll()
                    .map { list -> list.map { it.toLocalEntryModel() } }

            }
    }
}