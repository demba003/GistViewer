package com.miquido.gistsmvp.screen.gistdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.screen.gistlist.ListActivity
import kotlinx.android.synthetic.main.activity_gist.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

const val GIST = "gist"

class DetailsActivity : AppCompatActivity() {
    private val detailsViewModel: DetailsViewModel by viewModel()
    private val glide: RequestManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist)

        val gist: Gist? = intent?.extras?.get(GIST) as Gist?

        gist?.let {
            detailsViewModel.downloadUser(it.owner.login)
            detailsViewModel.downloadGistContent(it.id)
            initViews(gist)
            initObservers()
        } ?: goBackToList()
    }

    private fun initObservers() {
        detailsViewModel.gist.observe(this, Observer { showGistContent(it) })
        detailsViewModel.user.observe(this, Observer { updateUserData(it) })
        detailsViewModel.error.observe(this, Observer { if (it) showDownloadingError() })
    }

    private fun initViews(gist: Gist) {
        username.text = gist.owner.login
        glide.load(gist.owner.avatar_url).into(image)
        contentDescription.text = gist.description
    }

    private fun updateUserData(user: User) {
        followers.text = user.followers.toString()
        repos.text = user.public_repos.toString()
        headerCard.setOnClickListener { goToUserProfile(user) }
    }

    private fun showGistContent(gist: Gist) {
        val file = gist.files.entries.iterator().next()
        if (gist.description.isEmpty()) contentDescription.visibility = View.GONE
        contentText.text = file.value.content
        fileName.text = file.key
    }

    private fun showDownloadingError() {
        Toast.makeText(this, "Loading error", Toast.LENGTH_LONG).show()
    }

    private fun goToUserProfile(user: User) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(user.html_url)
            startActivity(this)
        }
    }

    private fun goBackToList() {
        Intent(this, ListActivity::class.java).apply {
            startActivity(this)
        }
    }
}
