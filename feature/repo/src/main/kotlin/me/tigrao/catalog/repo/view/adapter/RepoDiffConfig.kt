package me.tigrao.catalog.repo.view.adapter

import androidx.recyclerview.widget.DiffUtil
import me.tigrao.catalog.repo.domain.model.RepositoryDataModel

internal class RepoDiffConfig : DiffUtil.ItemCallback<RepositoryDataModel>() {
    override fun areItemsTheSame(oldItem: RepositoryDataModel, newItem: RepositoryDataModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RepositoryDataModel, newItem: RepositoryDataModel): Boolean {
        return oldItem == newItem
    }
}
