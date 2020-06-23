package com.miquido.gistsmvp.screen.gistlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miquido.gistsmvp.models.local.GistEntryModel
import com.miquido.gistsmvp.models.local.Result
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import kotlinx.coroutines.launch

class ListViewModel(
    private val getGistsUseCase: GetGistsUseCase
) : ViewModel() {

    val gists = MutableLiveData<Result<List<GistEntryModel>>>()

    fun downloadGists() {
        viewModelScope.launch {
            gists.value = Result.Loading()
            try {
                gists.value = Result.Success(getGistsUseCase.getGists())
            } catch (e: Exception) {
                gists.value = Result.Failure(e)
            }
        }
    }
}
