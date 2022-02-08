package me.tigrao.catalog.detail.presententation.model.data

internal sealed interface MovieDetailListItemType

internal data class SeasonModelUi(
    val name: String,
) : MovieDetailListItemType

internal data class EpisodeModelUI(
    val name: String,
    val summary: String,
    val info: String,
    val image: String,
) : MovieDetailListItemType
