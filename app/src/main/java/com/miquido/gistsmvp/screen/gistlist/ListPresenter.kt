package com.miquido.gistsmvp.screen.gistlist

import android.annotation.SuppressLint
import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.repository.GistsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class ListPresenter(
    private val gistsRepository: GistsRepository,
    private val schedulers: SchedulerProvider
) : ListContract.Presenter {
    private val disposables = CompositeDisposable()
    private lateinit var view: ListContract.View
    private var gists = mutableListOf<Gist>()

    override fun getGists(): List<Gist> {
        return gists
    }

    override fun init(view: ListContract.View) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    override fun downloadGists() {
        gistsRepository.getGists()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .doOnSubscribe { disposables.add(it) }
            .subscribeBy(
                onSuccess = {
                    gists.clear()
                    gists.addAll(it)
                    view.displayDownloadedGists()
                },
                onError = {
                    it.printStackTrace()
                    view.showDownloadingError()
                }
            )
    }

    override fun onCardClick(gist: Gist) {
        view.openGist(gist)
    }

    override fun dispose() {
        disposables.dispose()
    }
}