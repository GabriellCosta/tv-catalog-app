package me.tigrao.catalog.detail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import me.tigrao.catalog.detail.impl.databinding.ItemEpisodeBinding
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI

internal class EpisodesViewHolder(
    private val viewBinding: ItemEpisodeBinding,
) : EpisodesListViewHolder(viewBinding.root) {

    companion object {
        operator fun invoke(parent: ViewGroup): EpisodesListViewHolder =
            EpisodesViewHolder(
                ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    override fun bind(item: EpisodeModelUI) {
        viewBinding.txtContent.text = item.name
    }
}
