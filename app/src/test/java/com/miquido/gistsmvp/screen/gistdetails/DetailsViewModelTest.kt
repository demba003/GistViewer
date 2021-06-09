package com.miquido.gistsmvp.screen.gistdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.miquido.gistsmvp.models.local.GistDetailsModel
import com.miquido.gistsmvp.models.local.Result
import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.usecase.GetGistDetailsUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Test
    fun `when user details and gist are downloaded, live data should be updated`() {
        val user = UserModel(1, "", "", 1, 1, "", "")
        val getUserMock = mock<GetUserUseCase> {
            on { getUser(any()) } doReturn flow { emit(user) }
        }
        val details = GistDetailsModel("", "", "", mock())
        val getDetailsMock = mock<GetGistDetailsUseCase> {
            on { getGist(any()) } doReturn flow { emit(details) }
        }

        val viewModel = DetailsViewModel(getUserMock, getDetailsMock)

        viewModel.downloadUser("")
        viewModel.downloadGistContent("")

        assertTrue(viewModel.user.value is Result.Success)
        assertEquals((viewModel.user.value as Result.Success<UserModel>).value, user)

        assertTrue(viewModel.gist.value is Result.Success)
        assertEquals((viewModel.gist.value as Result.Success<GistDetailsModel>).value, details)
    }

    @Test
    fun `when there is an error, error should be passed to  live data`() {
        val getUserMock = mock<GetUserUseCase> {
            on { getUser(any()) } doReturn flow { throw Exception() }
        }
        val getDetailsMock = mock<GetGistDetailsUseCase> {
            on { getGist(any()) } doReturn flow { throw Exception() }
        }

        val viewModel = DetailsViewModel(getUserMock, getDetailsMock)

        viewModel.downloadUser("")
        viewModel.downloadGistContent("")

        assertTrue(viewModel.user.value is Result.Failure)
        assertTrue(viewModel.gist.value is Result.Failure)
    }
}
