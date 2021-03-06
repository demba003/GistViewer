package com.miquido.gistsmvp.screen.gistdetails

import android.annotation.SuppressLint
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class DetailsPresenter(
    private val getUserUseCase: GetUserUseCase,
    private val getGistUseCase: GetGistUseCase,
    private val schedulers: SchedulerProvider
) : DetailsContract.Presenter {
    private val disposables = CompositeDisposable()
    private lateinit var user: User
    private lateinit var fileGist: Gist
    private lateinit var view: DetailsContract.View
    private lateinit var gist: Gist

    override fun init(view: DetailsContract.View, gist: Gist) {
        this.view = view
        this.gist = gist
        view.initViews(gist)
    }

    override fun onHeaderClick() {
        view.goToUserProfile(user)
    }

    @SuppressLint("CheckResult")
    override fun downloadUser() {
        getUserUseCase.getUser(gist.owner.login)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .doOnSubscribe { disposables.add(it) }
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
    }

    @SuppressLint("CheckResult")
    override fun downloadGistContent() {
        getGistUseCase.getGist(gist.id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .doOnSubscribe { disposables.add(it) }
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
    }

    override fun dispose() {
        disposables.dispose()
    }

}