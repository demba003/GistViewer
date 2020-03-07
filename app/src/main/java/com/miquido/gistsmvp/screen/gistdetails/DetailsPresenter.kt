package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.repository.GistDetailsRepository
import com.miquido.gistsmvp.repository.UserRepository
import com.miquido.gistsmvp.models.local.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val gistDetailsRepository: GistDetailsRepository,
    private val schedulers: SchedulerProvider
) : DetailsContract.Presenter {
    private val disposables = CompositeDisposable()
    private lateinit var user: UserModel
    private lateinit var view: DetailsContract.View

    private var gistId: String = ""
    private var ownerLogin: String = ""

    override fun init(view: DetailsContract.View, gistId: String, ownerLogin: String) {
        this.view = view
        this.gistId = gistId
        this.ownerLogin = ownerLogin

        downloadUser()
        downloadGistContent()
    }

    override fun onHeaderClick() {
        view.goToUserProfile(user.profileUrl)
    }

    private fun downloadUser() {
        disposables += userRepository.getUser(ownerLogin)
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
    }

    private fun downloadGistContent() {
        disposables += gistDetailsRepository.getGist(gistId)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = {
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