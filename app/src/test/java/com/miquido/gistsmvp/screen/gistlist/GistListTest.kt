package com.miquido.gistsmvp.screen.gistlist

import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.schedulers.TestSchedulerProvider
import com.miquido.gistsmvp.repository.GistsRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GistListTest {
    private val sampleGist: Gist = mock()
    private val sampleGistList = listOf(sampleGist)
    private val view: ListContract.View = mock()
    private var gistsRepository: GistsRepository = mock()

    @Test
    fun displayDownloaded_whenDataDownloaded() {
        //given
        whenever(gistsRepository.getGists()).thenReturn(Single.just(sampleGistList))
        val presenter = ListPresenter(gistsRepository, TestSchedulerProvider())
        presenter.init(view)

        // when
        presenter.downloadGists()

        // then
        verify(view).displayDownloadedGists()
    }

    @Test
    fun displayError_whenDownloadingError() {
        //given
        whenever(gistsRepository.getGists()).thenReturn(Single.error(Throwable("Expected error")))
        val presenter = ListPresenter(gistsRepository, TestSchedulerProvider())
        presenter.init(view)

        // when
        presenter.downloadGists()

        // then
        verify(view).showDownloadingError()
    }

    @Test
    fun openGist_whenCardClicked() {
        //given
        val presenter = ListPresenter(gistsRepository, TestSchedulerProvider())
        presenter.init(view)

        // when
        presenter.onCardClick(sampleGist)

        // then
        verify(view).openGist(sampleGist)
    }
}
