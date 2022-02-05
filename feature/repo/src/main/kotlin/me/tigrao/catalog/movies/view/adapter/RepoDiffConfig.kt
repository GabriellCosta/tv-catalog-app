package me.tigrao.catalog.movies.view.adapter

import androidx.recyclerview.widget.DiffUtil
import me.tigrao.catalog.movies.domain.model.RepositoryDataModel

internal class RepoDiffConfig : DiffUtil.ItemCallback<RepositoryDataModel>() {
    override fun areItemsTheSame(oldItem: RepositoryDataModel, newItem: RepositoryDataModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RepositoryDataModel, newItem: RepositoryDataModel): Boolean {
        return oldItem == newItem
    }
}
