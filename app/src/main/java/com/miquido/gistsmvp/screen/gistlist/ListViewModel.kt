package com.miquido.gistsmvp.screen.gistlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miquido.gistmvp.usecase.GetGistsUseCase
import com.miquido.gistsmvp.domain.GistEntryModel
import com.miquido.gistsmvp.models.Result
import kotlinx.coroutines.launch

class ListViewModel(
    private val getGistsUseCase: GetGistsUseCase
) : ViewModel() {

    val gists = MutableLiveData<Result<List<GistEntryModel>>>()

    fun downloadGists() {
        viewModelScope.launch {
            gists.value = Result.Loading()
            try {
                gists.value = Result.Success(getGistsUseCase())
            } catch (e: Exception) {
                gists.value = Result.Failure(e)
            }
        }
    }
}
