package me.tigrao.catalog.movies.domain.model

internal data class MovieListModel(
    val data: List<MoviewListDataModel>,
)

internal data class MoviewListDataModel(
    val avatar: String,
    val title: String,
    val author: String,
    val stars: Int,
    val forks: Int,
    val description: String
)
