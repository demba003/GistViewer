package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User

interface DetailsContract {

    interface DetailsView {
        fun initViews(gist: Gist)
        fun updateUserData(user: User)
        fun showDownloadingError()
        fun goToUserProfile(user: User)
        fun showGistContent(gist: FileGist)
    }

    interface DetailsPresenter {
        fun downloadUser()
        fun onHeaderClick()
        fun downloadGistContent()
        fun dispose()
    }
}