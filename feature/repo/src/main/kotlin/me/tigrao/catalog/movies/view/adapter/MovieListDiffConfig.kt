package me.tigrao.catalog.movies.view.adapter

import androidx.recyclerview.widget.DiffUtil
import me.tigrao.catalog.movies.domain.model.MoviewListDataModel

internal class MovieListDiffConfig : DiffUtil.ItemCallback<MoviewListDataModel>() {
    override fun areItemsTheSame(oldItem: MoviewListDataModel, newItem: MoviewListDataModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MoviewListDataModel, newItem: MoviewListDataModel): Boolean {
        return oldItem == newItem
    }
}
