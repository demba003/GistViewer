package com.miquido.gistsmvp.screen.gistlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.local.GistEntryModel
import javax.inject.Inject

class GistAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<GistViewHolder>() {

    var onClickAction: (GistEntryModel) -> Unit = {}
    var gists: List<GistEntryModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_gist, parent, false)
        return GistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gists.size
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        val gist = gists[position]

        gist.ownerAvatarUrl?.let {
            glide.load(it).into(holder.avatar)
        } ?: glide.load(R.drawable.ic_githubmark).into(holder.avatar)


        holder.username.text = gist.ownerLogin
        holder.content.text = gist.description
        holder.card.setOnClickListener { onClickAction(gist) }
    }
}