package com.miquido.gistsmvp.screen.gistlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.schedulers.DeviceSchedulerProvider
import com.miquido.gistsmvp.screen.gistdetails.DetailsActivity
import com.miquido.gistsmvp.usecase.GetGistsUseCase
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), ListContract.ListView {
    private lateinit var presenter: ListContract.ListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        presenter = ListPresenter(this, GetGistsUseCase(), DeviceSchedulerProvider())
        presenter.downloadGists()
        swipeRefreshLayout.setOnRefreshListener(presenter::downloadGists)
    }

    override fun displayDownloadedGists(gists: List<Gist>) {
        val adapter = GistAdapter(this, gists, presenter::onCardClick)
        gistsRecycler.layoutManager = LinearLayoutManager(this)
        gistsRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
        hideRefreshingIndicator()
    }

    override fun showDownloadingError() {
        Toast.makeText(this, "Loading error", Toast.LENGTH_LONG).show()
        hideRefreshingIndicator()
    }

    private fun hideRefreshingIndicator() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun openGist(gist: Gist) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("gist", gist)
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}
