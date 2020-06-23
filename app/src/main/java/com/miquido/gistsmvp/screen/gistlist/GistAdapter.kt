package com.miquido.gistsmvp.screen.gistlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.local.GistEntryModel
import kotlinx.android.synthetic.main.element_gist.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class GistAdapter(
    private val onClickAction: (GistEntryModel) -> Unit
) : RecyclerView.Adapter<GistAdapter.GistViewHolder>(), KoinComponent {
    private val glide: RequestManager by inject()

    var gists: List<GistEntryModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_gist, parent, false)
        return GistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gists.size
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        holder.setData(gists[position])
    }

    inner class GistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(gist: GistEntryModel) {
            with(itemView) {
                glide.load(gist.ownerAvatarUrl).into(image)
                username.text = gist.ownerLogin
                content.text = gist.description
                card.setOnClickListener { onClickAction(gist) }
            }
        }
    }
}
