package com.miquido.gistsmvp.screen.gistdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User
import kotlinx.android.synthetic.main.activity_gist.*

class DetailsActivity : AppCompatActivity(), DetailsContract.DetailsView {
    private lateinit var presenter: DetailsContract.DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist)
        presenter = DetailsPresenter(this, intent.extras["gist"] as Gist)
        headerCard.setOnClickListener { presenter.onHeaderClick() }
    }

    override fun initViews(gist: Gist) {
        username.text = gist.owner.login
        Glide.with(this).load(gist.owner.avatar_url).into(image)
        contentText.text = gist.description
    }

    override fun updateUserData(user: User) {
        followers.text = user.followers.toString()
        repos.text = user.public_repos.toString()
    }

    override fun showGistContent(gist: FileGist) {
        val file = gist.files.entries.iterator().next()
        contentText.text = file.value.content
        fileName.text = file.key
    }

    override fun showDownloadingError() {
        Toast.makeText(this, "Loading error", Toast.LENGTH_LONG).show()
    }

    override fun goToUserProfile(user: User) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(user.html_url)
        startActivity(i)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}