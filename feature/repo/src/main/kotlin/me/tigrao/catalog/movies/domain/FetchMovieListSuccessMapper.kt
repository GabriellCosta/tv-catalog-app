package me.tigrao.catalog.movies.domain

import me.tigrao.catalog.movies.data.api.model.MovieListResponse
import me.tigrao.catalog.movies.domain.model.MoviewListDataModel
import me.tigrao.catalog.movies.domain.model.MovieListModel

internal class FetchMovieListSuccessMapper {

    fun mapFrom(from: MovieListResponse): MovieListModel {
        val data = from.items.map { map ->
            MoviewListDataModel(
                map.owner.avatar,
                map.name,
                map.owner.login,
                map.stars,
                map.forks,
                map.description.orEmpty()
            )
        }

        return MovieListModel(
            data = data,
        )
    }
}
