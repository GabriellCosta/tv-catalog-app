package me.tigrao.catalog.movies.domain

import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.movies.data.api.model.MovieListItemResponse
import me.tigrao.catalog.movies.domain.model.MovieListDataModel
import me.tigrao.catalog.movies.domain.model.MovieListModel
import me.tigrao.catalog.movies.presentation.model.MovieListAction

internal class FetchMovieListSuccessMapper {

    fun mapFrom(from: List<MovieListItemResponse>): MovieListModel {
        val data = from.map { map ->
            MovieListDataModel(
                image = map.image.medium,
                title = map.name,
                author = map.status,
                description = map.summary,
                action = MovieListAction.OpenDetail(
                    data = MovieDetailArgs(
                        id = map.id,
                        name = map.name,
                        image = map.image.original,
                        genreList = map.genres,
                        summary = map.summary,
                    )
                )
            )
        }

        return MovieListModel(
            data = data,
        )
    }
}
