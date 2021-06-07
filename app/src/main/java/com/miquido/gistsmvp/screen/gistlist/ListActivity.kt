package com.miquido.gistsmvp.screen.gistlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.local.GistEntryModel
import com.miquido.gistsmvp.screen.gistdetails.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : AppCompatActivity(), ListContract.View {

    @Inject
    lateinit var presenter: ListContract.Presenter

    @Inject
    lateinit var adapter: GistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initAdapter()
        swipeRefreshLayout.setOnRefreshListener(presenter::downloadGists)

        presenter.init(this)
    }

    private fun initAdapter() {
        adapter.onClickAction = presenter::onCardClick
        gistsRecycler.layoutManager = LinearLayoutManager(this)
        gistsRecycler.adapter = adapter
    }

    override fun displayDownloadedGists(list: List<GistEntryModel>) {
        adapter.gists = list
        hideRefreshingIndicator()
    }

    override fun showDownloadingError() {
        Toast.makeText(this, "Loading error", Toast.LENGTH_LONG).show()
        hideRefreshingIndicator()
    }

    private fun hideRefreshingIndicator() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun openGist(gist: GistEntryModel) {
        startActivity(DetailsActivity.newIntent(this, gist.id, gist.ownerLogin))
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}
