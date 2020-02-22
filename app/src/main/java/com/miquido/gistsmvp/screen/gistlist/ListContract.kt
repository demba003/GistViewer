package com.miquido.gistsmvp.screen.gistlist

import com.miquido.gistsmvp.models.local.GistEntryModel

interface ListContract {
    interface View {
        fun displayDownloadedGists()
        fun showDownloadingError()
        fun openGist(gist: GistEntryModel)
    }

    interface Presenter {
        fun init(view: View)
        fun getGists(): List<GistEntryModel>
        fun downloadGists()
        fun onCardClick(gist: GistEntryModel)
        fun dispose()
    }
}