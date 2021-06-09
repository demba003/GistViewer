package com.miquido.gistsmvp.screen.gistdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.miquido.gistsmvp.models.local.GistEntryModel
import com.miquido.gistsmvp.models.local.Result
import com.miquido.gistsmvp.screen.gistlist.ListViewModel
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Test
    fun `when gists are downloaded, live data should be updated`() {
        val gists = emptyList<GistEntryModel>()
        val getGistsMock = mock<GetGistsUseCase> {
            on { getGists() } doReturn flow { emit(gists) }
        }

        val viewModel = ListViewModel(getGistsMock)

        viewModel.downloadGists()

        assertTrue(viewModel.gists.value is Result.Success)
        assertEquals((viewModel.gists.value as Result.Success<List<GistEntryModel>>).value, gists)
    }

    @Test
    fun `when there is an error, it should be passed to live data`() {
        val getGistsMock = mock<GetGistsUseCase> {
            on { getGists() } doReturn flow { throw Exception() }
        }

        val viewModel = ListViewModel(getGistsMock)

        viewModel.downloadGists()

        assertTrue(viewModel.gists.value is Result.Failure)
    }
}
