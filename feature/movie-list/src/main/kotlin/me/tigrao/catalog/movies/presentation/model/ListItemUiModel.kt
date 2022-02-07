package me.tigrao.catalog.movies.presentation.model

internal data class ListItemUiModel(
    val avatar: String,
    val title: String,
    val author: String,
    val stars: Int,
    val forks: Int,
    val description: String
)
