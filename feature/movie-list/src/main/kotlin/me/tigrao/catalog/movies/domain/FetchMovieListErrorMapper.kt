package me.tigrao.catalog.movies.domain

import deb.tigrao.catalog.infra.api.ResultDomainError
import me.tigrao.catalog.movies.domain.model.MovieListErrorModel

private const val HTTP_CODE_FORBIDDEN = 403

internal class FetchMovieListErrorMapper {

    fun mapFrom(from: ResultDomainError): MovieListErrorModel {
        return when (from) {
            is ResultDomainError.GenericError -> MovieListErrorModel.GenericError
            is ResultDomainError.NetworkError -> {
                if (from.httpCode == HTTP_CODE_FORBIDDEN) {
                    MovieListErrorModel.MaxOfRequestReach
                } else {
                    MovieListErrorModel.GenericError
                }
            }
            ResultDomainError.UnknownError -> MovieListErrorModel.GenericError
        }
    }
}
