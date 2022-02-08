package me.tigrao.catalog.detail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tigrao.catalog.detail.impl.databinding.ItemSeasonBinding
import me.tigrao.catalog.detail.presententation.model.data.SeasonModelUi

internal class SeasonViewHolder(
    private val viewBinding: ItemSeasonBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {

    companion object {
        operator fun invoke(parent: ViewGroup) =
            SeasonViewHolder(
                ItemSeasonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    fun bind(item: SeasonModelUi) {
        viewBinding.txtContent.text = item.name
    }
}
