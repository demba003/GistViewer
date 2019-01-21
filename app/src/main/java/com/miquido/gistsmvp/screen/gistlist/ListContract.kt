package com.miquido.gistsmvp.screen.gistlist

import com.miquido.gistsmvp.models.Gist

interface ListContract {
    interface View {
        fun displayDownloadedGists()
        fun showDownloadingError()
        fun openGist(gist: Gist)
    }

    interface Presenter {
        fun init(view: View)
        fun getGists(): List<Gist>
        fun downloadGists()
        fun onCardClick(gist: Gist)
        fun dispose()
    }
}