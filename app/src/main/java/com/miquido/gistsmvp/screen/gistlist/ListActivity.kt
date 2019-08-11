package com.miquido.gistsmvp.screen.gistlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.Gist
import com.miquido.gistsmvp.screen.gistdetails.DetailsActivity
import com.miquido.gistsmvp.screen.gistdetails.GIST
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {
    private val listViewModel: ListViewModel by viewModel()
    private lateinit var adapter: GistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initAdapter()
        initObservers()
        swipeRefreshLayout.setOnRefreshListener(listViewModel::downloadGists)
        listViewModel.downloadGists()
    }

    private fun initAdapter() {
        adapter = GistAdapter(this::openGist)
        gistsRecycler.layoutManager = LinearLayoutManager(this)
        gistsRecycler.adapter = adapter
    }

    private fun initObservers() {
        listViewModel.error.observe(this, Observer { if (it) showDownloadingError() })
        listViewModel.gists.observe(this, Observer { displayDownloadedGists(it) })
    }

    private fun displayDownloadedGists(gists: List<Gist>) {
        adapter.gists = gists
        adapter.notifyDataSetChanged()
        hideRefreshingIndicator()
    }

    private fun showDownloadingError() {
        Toast.makeText(this, "Loading error", Toast.LENGTH_LONG).show()
        hideRefreshingIndicator()
    }

    private fun hideRefreshingIndicator() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun openGist(gist: Gist) {
        Intent(this, DetailsActivity::class.java).apply {
            putExtra(GIST, gist)
            startActivity(this)
        }
    }
}
