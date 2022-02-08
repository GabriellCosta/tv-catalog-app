package me.tigrao.catalog.movies.domain

import deb.tigrao.catalog.infra.api.ResultDomainError
import me.tigrao.catalog.movies.domain.model.MovieListErrorModel

internal class FetchMovieListErrorMapper {

    fun mapFrom(from: ResultDomainError): MovieListErrorModel {
        return when (from) {
            is ResultDomainError.GenericError -> MovieListErrorModel.GenericError
            is ResultDomainError.NetworkError -> MovieListErrorModel.GenericError
            ResultDomainError.UnknownError -> MovieListErrorModel.GenericError
        }
    }
}
