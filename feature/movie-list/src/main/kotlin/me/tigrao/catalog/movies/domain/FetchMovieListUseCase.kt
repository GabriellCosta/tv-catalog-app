package me.tigrao.catalog.movies.domain

import deb.tigrao.catalog.infra.api.ResultDomain
import me.tigrao.catalog.movies.domain.model.MovieListErrorModel
import me.tigrao.catalog.movies.domain.model.MovieListModel

internal interface FetchMovieListUseCase {
    suspend operator fun invoke(
        parameter: FetchMovieListParameters,
    ): ResultDomain<MovieListModel, MovieListErrorModel>
}

internal class FetchMovieListUseCaseImpl(
    private val withSearchUseCase: FetchMovieListSearchUseCase,
    private val noSearchUseCase: FetchMovieListWithoutSearchUseCase
) : FetchMovieListUseCase {
    override suspend fun invoke(
        parameter: FetchMovieListParameters,
    ): ResultDomain<MovieListModel, MovieListErrorModel> {
        return if (parameter.query.isNotEmpty()) {
            withSearchUseCase(parameter)
        } else {
            noSearchUseCase(parameter)
        }
    }
}

internal data class FetchMovieListParameters(
    val query: String = "",
    val page: Int,
)
