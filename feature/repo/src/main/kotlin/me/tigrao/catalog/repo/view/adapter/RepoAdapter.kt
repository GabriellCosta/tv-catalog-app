package me.tigrao.catalog.repo.view.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import me.tigrao.catalog.repo.domain.model.RepositoryDataModel

internal class RepoAdapter :
    PagingDataAdapter<RepositoryDataModel, RepoViewHolder>(RepoDiffConfig()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
