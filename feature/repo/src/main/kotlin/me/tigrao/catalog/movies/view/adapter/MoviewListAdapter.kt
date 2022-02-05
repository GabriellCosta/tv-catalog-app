package me.tigrao.catalog.movies.view.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import me.tigrao.catalog.movies.domain.model.MovieListDataModel

internal class MoviewListAdapter :
    PagingDataAdapter<MovieListDataModel, RepoViewHolder>(MovieListDiffConfig()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
