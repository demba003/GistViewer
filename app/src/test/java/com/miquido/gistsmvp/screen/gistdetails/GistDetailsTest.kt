package com.miquido.gistsmvp.screen.gistdetails

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
    private val sampleGist = Gist("id", Owner("login", "url"), "description", mapOf())
    private val view: DetailsContract.View = mock()
    private var getGistUseCase: GetGistUseCase = mock()
    private var getUserUseCase: GetUserUseCase = mock()

    @Test
    fun displayDownloadedUser_whenDownloadedProperly() {
        // given
        whenever(getUserUseCase.getUser(any())).thenReturn(Single.just(sampleUser))
        val presenter = DetailsPresenter(getUserUseCase, getGistUseCase, TestSchedulerProvider())
        presenter.init(view, sampleGist)

        // when
        presenter.downloadUser()

        // then
        verify(view).updateUserData(sampleUser)
    }

    @Test
    fun displayError_whenDownloadingUserError() {
        //given
        whenever(getUserUseCase.getUser(any())).thenReturn(Single.error(Throwable("Expected error")))
        val presenter = DetailsPresenter(getUserUseCase, getGistUseCase, TestSchedulerProvider())
        presenter.init(view, sampleGist)

        // when
        presenter.downloadUser()

        // then
        verify(view).showDownloadingError()
    }

    @Test
    fun displayDownloadedGist_whenDownloadedProperly() {
        // given
        whenever(getGistUseCase.getGist(any())).thenReturn(Single.just(sampleGist))
        val presenter = DetailsPresenter(getUserUseCase, getGistUseCase, TestSchedulerProvider())
        presenter.init(view, sampleGist)

        // when
        presenter.downloadGistContent()

        // then
        verify(view).showGistContent(sampleGist)
    }

    @Test
    fun displayError_whenDownloadingGistError() {
        // given
        whenever(getGistUseCase.getGist(any())).thenReturn(Single.error(Throwable("Expected error")))
        val presenter = DetailsPresenter(getUserUseCase, getGistUseCase, TestSchedulerProvider())
        presenter.init(view, sampleGist)

        // when
        presenter.downloadGistContent()

        // then
        verify(view).showDownloadingError()
    }

    @Test
    fun goToUserProfile_whenHeaderClicked() {
        // given
        whenever(getUserUseCase.getUser(any())).thenReturn(Single.just(sampleUser))
        val presenter = DetailsPresenter(getUserUseCase, getGistUseCase, TestSchedulerProvider())
        presenter.init(view, sampleGist)
        presenter.downloadUser()

        // when
        presenter.onHeaderClick()

        // then
        verify(view).goToUserProfile(sampleUser)
    }
}