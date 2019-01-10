package com.miquido.gistsmvp.screen.gistlist

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ListPresenter(private val view: ListContract.ListView) : ListContract.ListPresenter {
    private val getGistsUseCase = GetGistsUseCase()
    private val disposables = CompositeDisposable()

    override fun downloadGists() {
        disposables.add(
            getGistsUseCase.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        view.displayDownloadedGists(it)
                    },
                    onError = {
                        it.printStackTrace()
                        view.showDownloadingError()
                    }
                )
        )
    }

    override fun onRefresh() {
        downloadGists()
    }

    override fun onCardClick(gist: Gist) {
        view.openGist(gist)
    }

    override fun dispose() {
        disposables.dispose()
    }
}