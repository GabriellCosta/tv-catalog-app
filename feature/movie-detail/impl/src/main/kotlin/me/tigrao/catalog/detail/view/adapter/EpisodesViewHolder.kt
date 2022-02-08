package me.tigrao.catalog.detail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.tigrao.catalog.detail.impl.databinding.ItemEpisodeBinding
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI

internal class EpisodesViewHolder(
    private val binding: ItemEpisodeBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        operator fun invoke(parent: ViewGroup) =
            EpisodesViewHolder(
                ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    fun bind(item: EpisodeModelUI) {
        binding.title.text = item.name
        binding.description.text =
            HtmlCompat.fromHtml(item.summary, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.author.text = item.info

        with(binding.avatar) {
            Glide.with(this)
                .load(item.image)
                .apply(RequestOptions.circleCropTransform())
                .into(this)
        }
    }
}
