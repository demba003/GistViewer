package com.miquido.gistsmvp.screen.gistdetails

import android.annotation.SuppressLint
import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.repository.GistRepository
import com.miquido.gistsmvp.repository.UserRepository
import com.miquido.gistsmvp.models.local.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class DetailsPresenter(
    private val userRepository: UserRepository,
    private val gistRepository: GistRepository,
    private val schedulers: SchedulerProvider
) : DetailsContract.Presenter {
    private val disposables = CompositeDisposable()
    private lateinit var user: UserModel
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
        userRepository.getUser(gist.owner.login)
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
        gistRepository.getGist(gist.id)
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