package me.tigrao.catalog.movies.view.adapter

import androidx.recyclerview.widget.DiffUtil
import me.tigrao.catalog.movies.domain.model.MovieListDataModel

internal class MovieListDiffConfig : DiffUtil.ItemCallback<MovieListDataModel>() {
    override fun areItemsTheSame(oldItem: MovieListDataModel, newItem: MovieListDataModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MovieListDataModel, newItem: MovieListDataModel): Boolean {
        return oldItem == newItem
    }
}
