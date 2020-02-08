package com.miquido.gistsmvp.screen.gistdetails

import com.miquido.gistsmvp.models.local.UserModel
import com.miquido.gistsmvp.models.network.Gist
import com.miquido.gistsmvp.models.network.User

interface DetailsContract {

    interface View {
        fun initViews(gist: Gist)
        fun updateUserData(user: UserModel)
        fun showDownloadingError()
        fun goToUserProfile(user: UserModel)
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