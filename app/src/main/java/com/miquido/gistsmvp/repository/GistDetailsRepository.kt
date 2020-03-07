package com.miquido.gistsmvp.repository

import com.miquido.gistsmvp.db.GistDetailsDao
import com.miquido.gistsmvp.models.local.GistDetailsModel
import com.miquido.gistsmvp.models.local.toDbModel
import com.miquido.gistsmvp.models.local.toLocalModel
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GistDetailsRepository @Inject constructor(
    private val api: GistAPI,
    private val db: GistDetailsDao
) {
    fun getGist(id: String): Single<GistDetailsModel> {
        return api.getGist(id)
            .doOnSuccess {
                db.insertOrUpdate(it.toDbModel())
            }
            .map { it.toLocalModel() }
            .onErrorResumeNext {
                db.getAll()
                    .toObservable()
                    .flatMap { Observable.fromIterable(it) }
                    .filter { it.id == id }
                    .firstOrError()
                    .map { it.toLocalModel() }
            }
    }
}
