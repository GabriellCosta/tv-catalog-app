package me.tigrao.catalog.detail.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.catalog.detail.presententation.model.MovieDetailAction
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI

private const val VIEW_TYPE_SEASON = 0
private const val VIEW_TYPE_EPISODE = 1

internal class EpisodeListAdapter(
    private val items: List<EpisodeModelUI>
) : RecyclerView.Adapter<EpisodesListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesListViewHolder {
        return if (viewType == VIEW_TYPE_SEASON) {
            SeasonViewHolder(parent)
        } else {
            EpisodesViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: EpisodesListViewHolder, position: Int) {
        val currentItem = items[position]

        holder.bind(currentItem)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].action is MovieDetailAction.EpisodeClickAction) {
            VIEW_TYPE_EPISODE
        } else {
            VIEW_TYPE_SEASON
        }
    }

    override fun getItemCount(): Int = items.size
}
