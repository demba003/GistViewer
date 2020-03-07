package com.miquido.gistsmvp.screen.gistlist

import android.annotation.SuppressLint
import com.miquido.gistsmvp.models.local.GistEntryModel
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.repository.GistsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val gistsRepository: GistsRepository,
    private val schedulers: SchedulerProvider
) : ListContract.Presenter {
    private val disposables = CompositeDisposable()
    private lateinit var view: ListContract.View
    private var gists = mutableListOf<GistEntryModel>()

    override fun getGists(): List<GistEntryModel> {
        return gists
    }

    override fun init(view: ListContract.View) {
        this.view = view
        downloadGists()
    }

    override fun downloadGists() {
        disposables += gistsRepository.getGists()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = {
                    gists.clear()
                    gists.addAll(it)
                    view.displayDownloadedGists(it)
                },
                onError = {
                    it.printStackTrace()
                    view.showDownloadingError()
                }
            )
    }

    override fun onCardClick(gist: GistEntryModel) {
        view.openGist(gist)
    }

    override fun dispose() {
        disposables.dispose()
    }
}