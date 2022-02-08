package me.tigrao.catalog.movies.data

import me.tigrao.catalog.movies.domain.FetchMovieListUseCase

internal class MovieListDatasourceFactory(
    private val fetchRepositoryUseCase: FetchMovieListUseCase,
    private val movieListErrorModelToUiMapper: MovieListErrorModelToUiMapper,
) {

    fun getInstance(query: String) =
        MovieListDataSource(
            query, fetchRepositoryUseCase, movieListErrorModelToUiMapper
        )
}
