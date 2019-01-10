package com.miquido.gistsmvp.screen.gistlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miquido.gistsmvp.R
import com.miquido.gistsmvp.models.Gist

class GistAdapter(
    private val context: Context,
    private val gists: List<Gist>,
    private val onClickAction: (Gist) -> Unit
) : RecyclerView.Adapter<GistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.element_gist, parent, false)
        return GistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gists.size
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        val gist = gists[position]

        Glide.with(context).load(gist.owner.avatar_url).into(holder.avatar)
        holder.username.text = gist.owner.login
        holder.content.text = gist.description
        holder.card.setOnClickListener { onClickAction(gist) }
    }
}