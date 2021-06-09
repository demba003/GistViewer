package com.miquido.gistsmvp.screen.gistlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miquido.gistsmvp.models.local.GistEntryModel
import com.miquido.gistsmvp.models.local.Result
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ListViewModel(
    private val getGistsUseCase: GetGistsUseCase
) : ViewModel() {

    val gists = MutableLiveData<Result<List<GistEntryModel>>>()

    fun downloadGists() {
        viewModelScope.launch {
            getGistsUseCase.getGists()
                .onStart { gists.value = Result.Loading() }
                .catch { gists.value = Result.Failure(it) }
                .collect { gists.value = Result.Success(it) }
        }
    }
}
