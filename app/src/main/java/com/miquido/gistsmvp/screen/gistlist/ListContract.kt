package com.miquido.gistsmvp.screen.gistlist

import com.miquido.gistsmvp.models.Gist

interface ListContract {
    interface ListView {
        fun displayDownloadedGists(gists: List<Gist>)
        fun showDownloadingError()
        fun openGist(gist: Gist)
    }

    interface ListPresenter {
        fun downloadGists()
        fun onRefresh()
        fun onCardClick(gist: Gist)
        fun dispose()
    }
}