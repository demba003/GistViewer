package com.miquido.gistsmvp.screen.gistlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miquido.gistsmvp.models.local.GistEntryModel
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class ListViewModel(
    private val getGistsUseCase: GetGistsUseCase,
    private val schedulers: SchedulerProvider
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val gists = MutableLiveData<List<GistEntryModel>>()
    val error = MutableLiveData<Boolean>()

    fun downloadGists() {
        disposables += getGistsUseCase.getGists()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = {
                    gists.value = it
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
