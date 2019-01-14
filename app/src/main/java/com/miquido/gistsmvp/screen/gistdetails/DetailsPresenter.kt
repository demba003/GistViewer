package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class DetailsPresenter(
    private val view: DetailsContract.DetailsView,
    private val gist: Gist,
    private val getUserUseCase: GetUserUseCase,
    private val getGistUseCase: GetGistUseCase,
    private val schedulers: SchedulerProvider
) : DetailsContract.DetailsPresenter {
    private val disposables = CompositeDisposable()
    private lateinit var user: User
    private lateinit var fileGist: FileGist

    override fun init() {
        view.initViews(gist)
        downloadUser()
        downloadGistContent()
    }

    override fun onHeaderClick() {
        view.goToUserProfile(user)
    }

    override fun downloadUser() {
        disposables.add(
            getUserUseCase.get(gist.owner.login)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribeBy(
                    onSuccess = {
                        user = it
                        view.updateUserData(it)
                    },
                    onError = {
                        it.printStackTrace()
                        view.showDownloadingError()
                    }
                )
        )
    }

    override fun downloadGistContent() {
        disposables.add(
            getGistUseCase.get(gist.id)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribeBy(
                    onSuccess = {
                        fileGist = it
                        view.showGistContent(it)
                    },
                    onError = {
                        it.printStackTrace()
                        view.showDownloadingError()
                    }
                )
        )
    }

    override fun dispose() {
        disposables.dispose()
    }

}