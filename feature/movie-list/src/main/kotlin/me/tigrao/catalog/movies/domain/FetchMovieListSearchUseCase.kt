package me.tigrao.catalog.movies.domain

import deb.tigrao.catalog.infra.api.ResultDomain
import deb.tigrao.catalog.infra.api.callApi
import me.tigrao.catalog.movies.data.api.MovieListApi
import me.tigrao.catalog.movies.domain.mapper.FetchMovieListErrorMapper
import me.tigrao.catalog.movies.domain.mapper.FetchMovieListSearchSuccessMapper
import me.tigrao.catalog.movies.domain.model.MovieListErrorModel
import me.tigrao.catalog.movies.domain.model.MovieListModel

internal interface FetchMovieListSearchUseCase {
    suspend operator fun invoke(
        parameter: FetchMovieListParameters
    ): ResultDomain<MovieListModel, MovieListErrorModel>
}

internal class FetchMovieListSearchUseCaseDefault(
    private val api: MovieListApi,
    private val success: FetchMovieListSearchSuccessMapper,
    private val error: FetchMovieListErrorMapper,
) : FetchMovieListSearchUseCase {
    override suspend fun invoke(
        parameter: FetchMovieListParameters
    ): ResultDomain<MovieListModel, MovieListErrorModel> {
        return callApi {
            api.searchMovieList(
                query = parameter.query,
                page = parameter.page,
            )
        }.transformMap(success::mapFrom, error::mapFrom)
    }
}
