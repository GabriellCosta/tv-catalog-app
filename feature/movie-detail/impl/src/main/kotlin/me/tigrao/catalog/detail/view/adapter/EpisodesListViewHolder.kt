package me.tigrao.catalog.detail.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI

internal abstract class EpisodesListViewHolder(rootView: View) :
    RecyclerView.ViewHolder(rootView) {

    abstract fun bind(item: EpisodeModelUI)
}
