package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.local.GistDetailsModel
import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.models.network.User

interface DetailsContract {

    interface View {
        fun updateUserData(user: UserModel)
        fun showDownloadingError()
        fun showGistContent(gist: GistDetailsModel)
        fun goToUserProfile(profileUrl: String)
    }

    interface Presenter {
        fun onHeaderClick()
        fun dispose()
        fun init(view: View, gistId: String, ownerLogin: String)
    }
}