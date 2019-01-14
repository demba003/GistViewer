package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.Owner
import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.schedulers.TestSchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GistDetailsTest {
    private val sampleUser: User = mock ()
    private val sampleFileGist: FileGist = mock()
    private val sampleGist = Gist("id", Owner("login", "url"), "description")
    private val view: DetailsContract.DetailsView = mock()
    private var getGistUseCase: GetGistUseCase = mock()
    private var getUserUseCase: GetUserUseCase = mock()

    @Test
    fun displayDownloadedUser_whenDownloadedProperly() {
        // given
        whenever(getUserUseCase.get(any())).thenReturn(Single.just(sampleUser))
        val presenter = DetailsPresenter(view, sampleGist, getUserUseCase, getGistUseCase, TestSchedulerProvider())

        // when
        presenter.downloadUser()

        // then
        verify(view).updateUserData(sampleUser)
    }

    @Test
    fun displayError_whenDownloadingUserError() {
        //given
        whenever(getUserUseCase.get(any())).thenReturn(Single.error(Throwable("Expected error")))
        val presenter = DetailsPresenter(view, sampleGist, getUserUseCase, getGistUseCase, TestSchedulerProvider())

        // when
        presenter.downloadUser()

        // then
        verify(view).showDownloadingError()
    }

    @Test
    fun displayDownloadedGist_whenDownloadedProperly() {
        // given
        whenever(getGistUseCase.get(any())).thenReturn(Single.just(sampleFileGist))
        val presenter = DetailsPresenter(view, sampleGist, getUserUseCase, getGistUseCase, TestSchedulerProvider())

        // when
        presenter.downloadGistContent()

        // then
        verify(view).showGistContent(sampleFileGist)
    }

    @Test
    fun displayError_whenDownloadingGistError() {
        // given
        whenever(getGistUseCase.get(any())).thenReturn(Single.error(Throwable("Expected error")))
        val presenter = DetailsPresenter(view, sampleGist, getUserUseCase, getGistUseCase, TestSchedulerProvider())

        // when
        presenter.downloadGistContent()

        // then
        verify(view).showDownloadingError()
    }

    @Test
    fun goToUserProfile_whenHeaderClicked() {
        // given
        whenever(getUserUseCase.get(any())).thenReturn(Single.just(sampleUser))
        val presenter = DetailsPresenter(view, sampleGist, getUserUseCase, getGistUseCase, TestSchedulerProvider())
        presenter.downloadUser()

        // when
        presenter.onHeaderClick()

        // then
        verify(view).goToUserProfile(sampleUser)
    }
}