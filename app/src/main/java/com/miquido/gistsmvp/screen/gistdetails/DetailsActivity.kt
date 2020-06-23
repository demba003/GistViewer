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
import com.miquido.gistsmvp.models.local.Result
import com.miquido.gistsmvp.models.local.UserModel
import kotlinx.android.synthetic.main.activity_gist.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity(R.layout.activity_gist) {
    private val detailsViewModel: DetailsViewModel by viewModel()
    private val glide: RequestManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        detailsViewModel.userClicked.observe(this, Observer { goToUserProfile(it) })
    }

    private fun updateUserData(result: Result<UserModel>) {
        when (result) {
            is Result.Success -> {
                glide.load(result.value.avatarUrl).into(image)
                username.text = result.value.name
                followers.text = result.value.followersCount.toString()
                repos.text = result.value.reposCount.toString()
                progressBar.visibility = View.GONE
            }
            is Result.Failure -> {
                Toast.makeText(this, getString(R.string.loading_error), Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
            is Result.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun showGistContent(result: Result<GistDetailsModel>) {
        when (result) {
            is Result.Success -> {
                if (result.value.description.isEmpty()) contentDescription.visibility = View.GONE
                contentText.text = result.value.file.content
                fileName.text = result.value.file.filename
                progressBar.visibility = View.GONE
            }
            is Result.Failure -> {
                Toast.makeText(this, getString(R.string.loading_error), Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
            is Result.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun goToUserProfile(result: Result<UserModel>) {
        when (result) {
            is Result.Success -> {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(result.value.profileUrl)
                    startActivity(this)
                }
            }
            is Result.Failure -> {
                Toast.makeText(this, getString(R.string.loading_error), Toast.LENGTH_LONG).show()
            }
            is Result.Loading -> {
                Toast.makeText(this, getString(R.string.wait), Toast.LENGTH_LONG).show()
            }
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
