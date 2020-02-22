package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.models.network.Owner
import com.miquido.gistsmvp.repository.GistDetailsRepository
import com.miquido.gistsmvp.repository.UserRepository
import com.miquido.gistsmvp.schedulers.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GistDetailsTest {
    private val sampleGist = Gist(
        "id",
        Owner("login", "url"),
        "description",
        mapOf()
    )
    private val view: DetailsContract.View = mock()
    private var gistDetailsRepository: GistDetailsRepository = mock()
    private var userRepository: UserRepository = mock()

    @Test
    fun displayDownloadedUser_whenDownloadedProperly() {
        // given
        whenever(userRepository.getUser(any())).thenReturn(Single.just(mock()))
        whenever(gistDetailsRepository.getGist(any())).thenReturn(Single.just(mock()))
        val presenter =
            DetailsPresenter(userRepository, gistDetailsRepository, TestSchedulerProvider())

        // when
        presenter.init(view, sampleGist.id, sampleGist.owner.login)

        // then
        verify(view).updateUserData(any())
    }

    @Test
    fun displayError_whenDownloadingUserError() {
        //given
        whenever(userRepository.getUser(any())).thenReturn(Single.error(Throwable("Expected error")))
        whenever(gistDetailsRepository.getGist(any())).thenReturn(Single.error(Throwable("Expected error")))
        val presenter =
            DetailsPresenter(userRepository, gistDetailsRepository, TestSchedulerProvider())

        // when
        presenter.init(view, sampleGist.id, sampleGist.owner.login)

        // then
        verify(view, times(2)).showDownloadingError()
    }

    @Test
    fun displayDownloadedGist_whenDownloadedProperly() {
        // given
        whenever(userRepository.getUser(any())).thenReturn(Single.just(mock()))
        whenever(gistDetailsRepository.getGist(any())).thenReturn(Single.just(mock()))
        val presenter =
            DetailsPresenter(userRepository, gistDetailsRepository, TestSchedulerProvider())

        // when
        presenter.init(view, sampleGist.id, sampleGist.owner.login)

        // then
        verify(view).showGistContent(any())
    }

    @Test
    fun displayError_whenDownloadingGistError() {
        // given
        whenever(userRepository.getUser(any())).thenReturn(Single.error(Throwable("Expected error")))
        whenever(gistDetailsRepository.getGist(any())).thenReturn(Single.error(Throwable("Expected error")))
        val presenter =
            DetailsPresenter(userRepository, gistDetailsRepository, TestSchedulerProvider())

        // when
        presenter.init(view, sampleGist.id, sampleGist.owner.login)

        // then
        verify(view, times(2)).showDownloadingError()
    }

    @Test
    fun goToUserProfile_whenHeaderClicked() {
        // given
        whenever(userRepository.getUser(any())).thenReturn(
            Single.just(
                UserModel(
                    1,
                    "login",
                    "name",
                    1,
                    1,
                    "url",
                    "url"
                )
            )
        )
        whenever(gistDetailsRepository.getGist(any())).thenReturn(Single.just(mock()))
        val presenter =
            DetailsPresenter(userRepository, gistDetailsRepository, TestSchedulerProvider())
        presenter.init(view, sampleGist.id, sampleGist.owner.login)

        // when
        presenter.onHeaderClick()

        // then
        verify(view).goToUserProfile(any())
    }
}