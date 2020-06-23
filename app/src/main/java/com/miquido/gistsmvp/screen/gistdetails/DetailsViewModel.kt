package com.miquido.gistsmvp.screen.gistdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miquido.gistsmvp.models.local.GistDetailsModel
import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistDetailsUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class DetailsViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getGistDetailsUseCase: GetGistDetailsUseCase,
    private val schedulers: SchedulerProvider
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val user = MutableLiveData<UserModel>()
    val gist = MutableLiveData<GistDetailsModel>()
    val error = MutableLiveData<Boolean>()
    val userClicked = MutableLiveData<UserModel>()

    fun downloadUser(login: String) {
        disposables += getUserUseCase.getUser(login)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = {
                    user.value = it
                    error.value = false
                },
                onError = {
                    it.printStackTrace()
                    error.value = true
                }
            )
    }

    fun downloadGistContent(id: String) {
        disposables += getGistDetailsUseCase.getGist(id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .doOnSubscribe { disposables.add(it) }
            .subscribeBy(
                onSuccess = {
                    gist.value = it
                    error.value = false
                },
                onError = {
                    it.printStackTrace()
                    error.value = true
                }
            )
    }

    fun headerClicked() {
        userClicked.value = user.value
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}