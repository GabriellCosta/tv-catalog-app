package me.tigrao.catalog.movies.domain.mapper

import me.tigrao.catalog.movies.data.api.model.MovieListItemSearchResponse
import me.tigrao.catalog.movies.domain.model.MovieListModel

internal class FetchMovieListSearchSuccessMapper(
    private val fetchMovieListSuccessMapper: FetchMovieListSuccessMapper,
) {

    fun mapFrom(from: List<MovieListItemSearchResponse>): MovieListModel {
        val data = from.map { map ->
            map.show
        }
        return fetchMovieListSuccessMapper.mapFrom(data)
    }
}
