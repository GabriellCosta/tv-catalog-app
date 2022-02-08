package me.tigrao.catalog.detail.domain.model

internal data class EpisodeListModel(
    val data: List<EpisodeListDataModel>,
)

internal data class EpisodeListDataModel(
    val season: String,
    val episodes: List<EpisodeItemModel>
)

internal data class EpisodeItemModel(
    val id: Long,
    val name: String,
    val image: String,
    val summary: String,
)
