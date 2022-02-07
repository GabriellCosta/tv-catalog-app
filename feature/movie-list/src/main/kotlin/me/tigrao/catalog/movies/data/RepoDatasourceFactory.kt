package me.tigrao.catalog.movies.data

import me.tigrao.catalog.movies.domain.FetchMovieListUseCase

internal class RepoDatasourceFactory(
    private val fetchRepositoryUseCase: FetchMovieListUseCase,
    private val repositoryErrorModelToUiMapper: RepositoryErrorModelToUiMapper,
) {

    fun getInstance(query: String) =
        RepoDataSource(
            query, fetchRepositoryUseCase, repositoryErrorModelToUiMapper
        )
}
