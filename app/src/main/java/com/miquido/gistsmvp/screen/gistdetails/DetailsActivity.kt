package com.miquido.gistsmvp.screen.gistdetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.local.GistDetailsModel
import com.miquido.gistsmvp.models.local.UserModel
import kotlinx.android.synthetic.main.activity_gist.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {
    private val detailsViewModel: DetailsViewModel by viewModel()
    private val glide: RequestManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist)

        val gistId = intent?.extras?.getString(EXTRA_GIST_ID) ?: ""
        val ownerLogin = intent?.extras?.getString(EXTRA_OWNER_LOGIN) ?: ""

        initObservers()
        headerCard.setOnClickListener { detailsViewModel.headerClicked() }
        detailsViewModel.downloadUser(ownerLogin)
        detailsViewModel.downloadGistContent(gistId)
    }

    private fun initObservers() {
        detailsViewModel.gist.observe(this, Observer { showGistContent(it) })
        detailsViewModel.user.observe(this, Observer { updateUserData(it) })
        detailsViewModel.error.observe(this, Observer { if (it) showDownloadingError() })
        detailsViewModel.userClicked.observe(this, Observer { goToUserProfile(it) })
    }

    private fun updateUserData(user: UserModel) {
        glide.load(user.avatarUrl).into(image)
        username.text = user.name
        followers.text = user.followersCount.toString()
        repos.text = user.reposCount.toString()
    }

    private fun showGistContent(gist: GistDetailsModel) {
        if (gist.description.isEmpty()) contentDescription.visibility = View.GONE
        contentText.text = gist.file.content
        fileName.text = gist.file.filename
    }

    private fun showDownloadingError() {
        Toast.makeText(this, "Loading error", Toast.LENGTH_LONG).show()
    }

    private fun goToUserProfile(user: UserModel) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(user.profileUrl)
            startActivity(this)
        }
    }

    companion object {
        private const val EXTRA_GIST_ID = "EXTRA_GIST_ID"
        private const val EXTRA_OWNER_LOGIN = "EXTRA_OWNER_LOGIN"

        fun newIntent(context: Context?, gistId: String, ownerLogin: String) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(EXTRA_GIST_ID, gistId)
                putExtra(EXTRA_OWNER_LOGIN, ownerLogin)
            }
    }
}
