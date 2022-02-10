package me.tigrao.catalog.movies.domain.mapper

import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.detail.view.ScheduleArgs
import me.tigrao.catalog.movies.data.api.model.MovieListItemResponse
import me.tigrao.catalog.movies.domain.model.MovieListDataModel
import me.tigrao.catalog.movies.domain.model.MovieListModel
import me.tigrao.catalog.movies.domain.model.ScheduleModel
import me.tigrao.catalog.movies.presentation.model.MovieListAction

internal class FetchMovieListSuccessMapper {

    fun mapFrom(from: List<MovieListItemResponse>): MovieListModel {
        val data = from.map { map ->
            MovieListDataModel(
                image = map.image.medium,
                title = map.name,
                author = map.status,
                description = map.summary,
                schedule = ScheduleModel(
                    time = map.schedule.time,
                    weekDay = map.schedule.days,
                ),
                action = MovieListAction.OpenDetail(
                    data = MovieDetailArgs(
                        id = map.id,
                        name = map.name,
                        image = map.image.original,
                        genreList = map.genres,
                        summary = map.summary,
                        schedule = ScheduleArgs(
                            time = map.schedule.time,
                            weekDay = map.schedule.days,
                        )
                    )
                )
            )
        }

        return MovieListModel(
            data = data,
        )
    }
}
