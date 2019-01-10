package com.miquido.gistsmvp.screen.gistlist

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class ListPresenter(
    private val view: ListContract.ListView,
    private val getGistsUseCase: GetGistsUseCase,
    private val schedulers: SchedulerProvider
) : ListContract.ListPresenter {
    private val disposables = CompositeDisposable()

    override fun downloadGists() {
        disposables.add(
            getGistsUseCase.get()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
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

    override fun onCardClick(gist: Gist) {
        view.openGist(gist)
    }

    override fun dispose() {
        disposables.dispose()
    }
}