package com.miquido.gistsmvp.screen.gistdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miquido.gistsmvp.models.local.GistDetailsModel
import com.miquido.gistsmvp.models.local.Result
import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.usecase.GetGistDetailsUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getGistDetailsUseCase: GetGistDetailsUseCase
) : ViewModel() {

    val user = MutableLiveData<Result<UserModel>>()
    val gist = MutableLiveData<Result<GistDetailsModel>>()
    val userClicked = MutableLiveData<Result<UserModel>>()

    fun downloadUser(login: String) {
        viewModelScope.launch {
            getUserUseCase.getUser(login)
                .onStart { user.value = Result.Loading() }
                .catch { user.value = Result.Failure(it) }
                .collect { user.value = Result.Success(it) }
        }
    }

    fun downloadGistContent(id: String) {
        viewModelScope.launch {
            getGistDetailsUseCase.getGist(id)
                .onStart { gist.value = Result.Loading() }
                .catch { gist.value = Result.Failure(it) }
                .collect { gist.value = Result.Success(it) }
        }
    }

    fun headerClicked() {
        userClicked.value = user.value
    }

}
