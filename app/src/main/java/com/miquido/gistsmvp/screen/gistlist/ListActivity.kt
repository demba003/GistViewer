package com.miquido.gistsmvp.screen.gistlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.domain.GistEntryModel
import com.miquido.gistsmvp.screen.gistdetails.DetailsActivity
import com.miquido.gistsmvp.models.Result
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity(R.layout.activity_list) {
    private val listViewModel: ListViewModel by viewModel()
    private lateinit var adapter: GistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initObservers()

        listViewModel.downloadGists()
    }

    private fun initAdapter() {
        adapter = GistAdapter(this::openGist)
        gistsRecycler.layoutManager = LinearLayoutManager(this)
        gistsRecycler.adapter = adapter
    }

    private fun initObservers() {
        listViewModel.gists.observe(this, Observer { displayDownloadedGists(it) })
        swipeRefreshLayout.setOnRefreshListener(listViewModel::downloadGists)
    }

    private fun displayDownloadedGists(result: Result<List<GistEntryModel>>) {
        when(result) {
            is Result.Success -> {
                adapter.gists = result.value
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

        hideRefreshingIndicator()
    }

    private fun hideRefreshingIndicator() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun openGist(gist: GistEntryModel) {
        startActivity(DetailsActivity.newIntent(this, gist.id, gist.ownerLogin))
    }
}
