package com.miquido.gistsmvp.screen.gistdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class DetailsViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getGistUseCase: GetGistUseCase,
    private val schedulers: SchedulerProvider
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val user = MutableLiveData<User>()
    val gist = MutableLiveData<Gist>()
    val error = MutableLiveData<Boolean>()

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
        disposables += getGistUseCase.getGist(id)
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

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}