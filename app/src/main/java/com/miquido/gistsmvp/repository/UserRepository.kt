package com.miquido.gistsmvp.repository

import com.miquido.gistsmvp.db.UserDao
import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.models.local.toDbModel
import com.miquido.gistsmvp.models.local.toLocalModel
import com.miquido.gistsmvp.network.GistAPI
import io.reactivex.Observable
import io.reactivex.Single

class UserRepository(
    private val api: GistAPI,
    private val db: UserDao
) {
    fun getUser(login: String): Single<UserModel> {
        return api.getUser(login)
            .doOnSuccess {
                db.insertOrUpdate(it.toDbModel())
            }
            .map { it.toLocalModel() }
            .onErrorResumeNext {
                db.getAll()
                    .toObservable()
                    .flatMap { Observable.fromIterable(it) }
                    .filter { it.login == login }
                    .firstOrError()
                    .map { it.toLocalModel() }
            }
    }

}