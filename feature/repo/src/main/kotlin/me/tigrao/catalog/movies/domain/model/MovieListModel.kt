package me.tigrao.catalog.movies.domain.model

internal data class MovieListModel(
    val data: List<MovieListDataModel>,
)

internal data class MovieListDataModel(
    val image: String,
    val title: String,
    val author: String,
    val description: String
)
