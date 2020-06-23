package com.miquido.gistsmvp.screen.gistdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miquido.gistsmvp.models.local.GistDetailsModel
import com.miquido.gistsmvp.models.local.Result
import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.usecase.GetGistDetailsUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
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
            user.value = Result.Loading()
            try {
                user.value = Result.Success(getUserUseCase.getUser(login))
            } catch (e: Exception) {
                user.value = Result.Failure(e)
            }
        }
    }

    fun downloadGistContent(id: String) {
        viewModelScope.launch {
            gist.value = Result.Loading()
            try {
                gist.value = Result.Success(getGistDetailsUseCase.getGist(id))
            } catch (e: Exception) {
                gist.value = Result.Failure(e)
            }
        }
    }

    fun headerClicked() {
        userClicked.value = user.value
    }

}