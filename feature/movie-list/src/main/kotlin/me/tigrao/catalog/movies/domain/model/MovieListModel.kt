package me.tigrao.catalog.movies.domain.model

import me.tigrao.catalog.movies.presentation.model.MovieListAction

internal data class MovieListModel(
    val data: List<MovieListDataModel>,
)

internal data class MovieListDataModel(
    val image: String,
    val title: String,
    val author: String,
    val description: String,
    val action: MovieListAction,
)
