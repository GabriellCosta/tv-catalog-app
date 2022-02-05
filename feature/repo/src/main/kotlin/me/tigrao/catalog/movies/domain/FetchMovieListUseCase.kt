package me.tigrao.catalog.movies.domain

import deb.tigrao.catalog.infra.api.ResultDomain
import deb.tigrao.catalog.infra.api.callApi
import me.tigrao.catalog.movies.data.api.MovieListApi
import me.tigrao.catalog.movies.domain.model.MovieListErrorModel
import me.tigrao.catalog.movies.domain.model.MovieListModel

internal interface FetchMovieListUseCase {
    suspend operator fun invoke(
        parameter: FetchMovieListParameters,
    ): ResultDomain<MovieListModel, MovieListErrorModel>
}

internal class FetchMovieListUseCaseImpl(
    private val api: MovieListApi,
    private val success: FetchMovieListSuccessMapper,
    private val error: FetchMovieListErrorMapper,
) : FetchMovieListUseCase {
    override suspend fun invoke(
        parameter: FetchMovieListParameters,
    ): ResultDomain<MovieListModel, MovieListErrorModel> {
        return callApi {
            api.fetchMovieList(
                language = parameter.language,
                sort = parameter.sort,
                page = parameter.page,
            )
        }.transformMap(success::mapFrom, error::mapFrom)
    }
}

internal data class FetchMovieListParameters(
    val language: String,
    val sort: String,
    val page: Int,
)
