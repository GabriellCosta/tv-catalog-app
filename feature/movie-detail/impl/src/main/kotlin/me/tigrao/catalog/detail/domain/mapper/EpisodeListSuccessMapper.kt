package me.tigrao.catalog.detail.domain.mapper

import me.tigrao.catalog.detail.data.model.EpisodeListResponse
import me.tigrao.catalog.detail.domain.model.EpisodeItemModel
import me.tigrao.catalog.detail.domain.model.EpisodeListDataModel
import me.tigrao.catalog.detail.domain.model.EpisodeListModel

internal class EpisodeListSuccessMapper {

    fun mapFrom(from: List<EpisodeListResponse>): EpisodeListModel {
        val episodesList = mutableListOf<EpisodeListDataModel>()

        val groupedBySeason = from.groupBy {
            it.season
        }

        groupedBySeason.forEach { entry ->
            val current = EpisodeListDataModel(
                season = entry.key.toString(),
                episodes = entry.value.map { currentEp ->
                    EpisodeItemModel(
                        id = currentEp.id,
                        name = currentEp.name,
                        summary = currentEp.summary,
                        image = currentEp.image.medium,
                    )
                }
            )

            episodesList.add(current)
        }

        episodesList.sortBy { it.season }

        return EpisodeListModel(
            data = episodesList
        )
    }
}
