package com.miquido.gistsmvp.screen.gistdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.FileGist
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.models.User
import com.miquido.gistsmvp.schedulers.SchedulerProvider
import com.miquido.gistsmvp.usecase.GetGistUseCase
import com.miquido.gistsmvp.usecase.GetUserUseCase
import kotlinx.android.synthetic.main.activity_gist.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class DetailsActivity : AppCompatActivity(), DetailsContract.DetailsView, KoinComponent {
    private val getUserUseCase: GetUserUseCase by inject()
    private val getGistUseCase: GetGistUseCase by inject()
    private val schedulerProvider: SchedulerProvider by inject()
    private lateinit var presenter: DetailsContract.DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gist)

        val gist = intent.extras["gist"] as Gist
        presenter = DetailsPresenter(this, gist, getUserUseCase, getGistUseCase, schedulerProvider)
        presenter.init()
        headerCard.setOnClickListener { presenter.onHeaderClick() }
    }

    override fun initViews(gist: Gist) {
        username.text = gist.owner.login
        Glide.with(this).load(gist.owner.avatar_url).into(image)
        contentDescription.text = gist.description
    }

    override fun updateUserData(user: User) {
        followers.text = user.followers.toString()
        repos.text = user.public_repos.toString()
    }

    override fun showGistContent(gist: FileGist) {
        val file = gist.files.entries.iterator().next()
        if (gist.description.isEmpty()) contentDescription.visibility = View.GONE
        contentText.text = file.value.content
        fileName.text = file.key
    }

    override fun showDownloadingError() {
        Toast.makeText(this, "Loading error", Toast.LENGTH_LONG).show()
    }

    override fun goToUserProfile(user: User) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(user.html_url)
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}