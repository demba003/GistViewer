package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User

interface DetailsContract {

    interface View {
        fun initViews(gist: Gist)
        fun updateUserData(user: User)
        fun showDownloadingError()
        fun goToUserProfile(user: User)
        fun showGistContent(gist: Gist)
    }

    interface Presenter {
        fun init(view: View, gist: Gist)
        fun downloadUser()
        fun onHeaderClick()
        fun downloadGistContent()
        fun dispose()
    }
}