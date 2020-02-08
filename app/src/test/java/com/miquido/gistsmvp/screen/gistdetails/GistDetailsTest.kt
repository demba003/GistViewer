package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.models.network.Owner
import com.miquido.gistsmvp.schedulers.TestSchedulerProvider
import com.miquido.gistsmvp.repository.GistRepository
import com.miquido.gistsmvp.repository.UserRepository
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
    private val sampleUser: UserModel = mock ()
    private val sampleGist = Gist(
        "id",
        Owner("login", "url"),
        "description",
        mapOf()
    )
    private val view: DetailsContract.View = mock()
    private var gistRepository: GistRepository = mock()
    private var userRepository: UserRepository = mock()

    @Test
    fun displayDownloadedUser_whenDownloadedProperly() {
        // given
        whenever(userRepository.getUser(any())).thenReturn(Single.just(sampleUser))
        val presenter = DetailsPresenter(userRepository, gistRepository, TestSchedulerProvider())
        presenter.init(view, sampleGist)

        // when
        presenter.downloadUser()

        // then
        verify(view).updateUserData(sampleUser)
    }

    @Test
    fun displayError_whenDownloadingUserError() {
        //given
        whenever(userRepository.getUser(any())).thenReturn(Single.error(Throwable("Expected error")))
        val presenter = DetailsPresenter(userRepository, gistRepository, TestSchedulerProvider())
        presenter.init(view, sampleGist)

        // when
        presenter.downloadUser()

        // then
        verify(view).showDownloadingError()
    }

    @Test
    fun displayDownloadedGist_whenDownloadedProperly() {
        // given
        whenever(gistRepository.getGist(any())).thenReturn(Single.just(sampleGist))
        val presenter = DetailsPresenter(userRepository, gistRepository, TestSchedulerProvider())
        presenter.init(view, sampleGist)

        // when
        presenter.downloadGistContent()

        // then
        verify(view).showGistContent(sampleGist)
    }

    @Test
    fun displayError_whenDownloadingGistError() {
        // given
        whenever(gistRepository.getGist(any())).thenReturn(Single.error(Throwable("Expected error")))
        val presenter = DetailsPresenter(userRepository, gistRepository, TestSchedulerProvider())
        presenter.init(view, sampleGist)

        // when
        presenter.downloadGistContent()

        // then
        verify(view).showDownloadingError()
    }

    @Test
    fun goToUserProfile_whenHeaderClicked() {
        // given
        whenever(userRepository.getUser(any())).thenReturn(Single.just(sampleUser))
        val presenter = DetailsPresenter(userRepository, gistRepository, TestSchedulerProvider())
        presenter.init(view, sampleGist)
        presenter.downloadUser()

        // when
        presenter.onHeaderClick()

        // then
        verify(view).goToUserProfile(sampleUser)
    }
}