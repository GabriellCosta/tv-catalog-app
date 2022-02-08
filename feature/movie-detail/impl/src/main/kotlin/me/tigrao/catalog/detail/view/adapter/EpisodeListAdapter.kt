package me.tigrao.catalog.detail.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI
import me.tigrao.catalog.detail.presententation.model.data.MovieDetailListItemType
import me.tigrao.catalog.detail.presententation.model.data.SeasonModelUi

private const val VIEW_TYPE_SEASON = 0
private const val VIEW_TYPE_EPISODE = 1

internal class EpisodeListAdapter(
    private val items: List<MovieDetailListItemType>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SEASON) {
            SeasonViewHolder(parent)
        } else {
            EpisodesViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = items[position]

        when (holder) {
            is EpisodesViewHolder -> holder.bind(currentItem as EpisodeModelUI)
            is SeasonViewHolder -> holder.bind(currentItem as SeasonModelUi)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is EpisodeModelUI) {
            VIEW_TYPE_EPISODE
        } else {
            VIEW_TYPE_SEASON
        }
    }

    override fun getItemCount(): Int = items.size
}
