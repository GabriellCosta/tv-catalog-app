package me.tigrao.catalog.detail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.tigrao.catalog.detail.impl.databinding.ItemEpisodeBinding
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI

internal class EpisodesViewHolder(
    private val binding: ItemEpisodeBinding,
) : EpisodesListViewHolder(binding.root) {

    companion object {
        operator fun invoke(parent: ViewGroup): EpisodesListViewHolder =
            EpisodesViewHolder(
                ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    override fun bind(item: EpisodeModelUI) {
        binding.txtContent.text = item.name
    }
}
