package me.tigrao.catalog.detail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import me.tigrao.catalog.detail.impl.databinding.ItemSeasonBinding
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI

internal class SeasonViewHolder(
    private val viewBinding: ItemSeasonBinding,
) : EpisodesListViewHolder(viewBinding.root) {

    companion object {
        operator fun invoke(parent: ViewGroup): EpisodesListViewHolder =
            SeasonViewHolder(
                ItemSeasonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    override fun bind(item: EpisodeModelUI) {
        viewBinding.txtContent.text = item.name
    }
}
