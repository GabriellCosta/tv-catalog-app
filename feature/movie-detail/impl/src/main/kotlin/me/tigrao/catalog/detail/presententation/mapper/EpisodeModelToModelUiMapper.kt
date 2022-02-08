package me.tigrao.catalog.detail.presententation.mapper

import android.content.res.Resources
import me.tigrao.catalog.detail.domain.model.EpisodeListModel
import me.tigrao.catalog.detail.impl.R
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI
import me.tigrao.catalog.detail.presententation.model.data.MovieDetailListItemType
import me.tigrao.catalog.detail.presententation.model.data.SeasonModelUi
import okhttp3.internal.toImmutableList

internal class EpisodeModelToModelUiMapper(
    private val resources: Resources,
) {

    fun mapFrom(from: EpisodeListModel): List<MovieDetailListItemType> {
        val result = mutableListOf<MovieDetailListItemType>()

        from.data.forEach { model ->
            result.add(
                SeasonModelUi(
                    name = model.season,
                )
            )

            result.addAll(model.episodes.map { episodesModel ->
                EpisodeModelUI(
                    name = episodesModel.name,
                    summary = episodesModel.summary,
                    info = resources.getString(
                        R.string.movie_detail_episode_info,
                        episodesModel.season,
                        episodesModel.number
                    ),
                    image = episodesModel.image,
                )
            })

        }
        return result.toImmutableList()
    }
}
