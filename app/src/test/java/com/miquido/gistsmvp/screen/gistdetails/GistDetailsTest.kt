package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.models.Gist
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
    val sampleUser: User = mock ()
    val sampleFileGist: FileGist = mock()
    val sampleGist: Gist = mock()
    val view: DetailsContract.DetailsView = mock()
    var getGistUseCase: GetGistUseCase = mock()
    var getUserUseCase: GetUserUseCase = mock()

    @Test
    fun displayDownloadedUser_whenDownloadedProperly() {
        // given
        //whenever(getUserUseCase.get(any())).thenReturn(Single.just(sampleUser))
        //val presenter = DetailsPresenter(view, sampleGist, getUserUseCase, getGistUseCase, TestSchedulerProvider())

        // when
        //presenter.downloadUser()

        // then
        //verify(view).updateUserData(sampleUser)
    }
}