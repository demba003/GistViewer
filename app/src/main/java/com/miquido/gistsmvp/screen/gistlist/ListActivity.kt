package com.miquido.gistsmvp.screen.gistlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.screen.gistdetails.DetailsActivity
import com.miquido.gistsmvp.screen.gistdetails.GIST
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ListActivity : AppCompatActivity(), ListContract.View, KoinComponent {
    private val presenter: ListContract.Presenter by inject()
    private lateinit var adapter: GistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initAdapter()
        swipeRefreshLayout.setOnRefreshListener(presenter::downloadGists)

        presenter.init(this)
        presenter.downloadGists()
    }

    private fun initAdapter() {
        adapter = GistAdapter(presenter.getGists(), presenter::onCardClick)
        gistsRecycler.layoutManager = LinearLayoutManager(this)
        gistsRecycler.adapter = adapter
    }

    override fun displayDownloadedGists() {
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
        Intent(this, DetailsActivity::class.java).apply {
            putExtra(GIST, gist)
            startActivity(this)
        }
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}
