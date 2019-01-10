package com.miquido.gistsmvp.screen.gistlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.element_gist.view.*

class GistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatar: ImageView = itemView.image
    val username: TextView = itemView.username
    val content: TextView = itemView.content
    val card: CardView = itemView.card
}