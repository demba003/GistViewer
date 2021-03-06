package com.miquido.gistsmvp.screen.gistlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.Gist
import org.koin.core.KoinComponent
import org.koin.core.inject

class GistAdapter(
    private val gists: List<Gist>,
    private val onClickAction: (Gist) -> Unit
) : RecyclerView.Adapter<GistViewHolder>(), KoinComponent {
    private val glide: RequestManager by inject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_gist, parent, false)
        return GistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gists.size
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        val gist = gists[position]

        glide.load(gist.owner.avatar_url).into(holder.avatar)
        holder.username.text = gist.owner.login
        holder.content.text = gist.description
        holder.card.setOnClickListener { onClickAction(gist) }
    }
}