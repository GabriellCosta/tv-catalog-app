package me.tigrao.catalog.movies.data

import me.tigrao.catalog.movies.domain.FetchMovieListUseCase

internal class MovieListPageDatasourceFactory(
    private val fetchRepositoryUseCase: FetchMovieListUseCase,
    private val movieListErrorModelToUiMapper: MovieListErrorModelToUiMapper,
) {

    fun getInstance(query: String) =
        MovieListPageDataSource(
            query, fetchRepositoryUseCase, movieListErrorModelToUiMapper
        )
}
