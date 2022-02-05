package me.tigrao.catalog.movies.domain

import deb.tigrao.catalog.infra.api.ResultDomainError
import me.tigrao.catalog.movies.domain.model.RepositoryErrorModel

private const val HTTP_CODE_FORBIDDEN = 403

internal class FetchRepositoryErrorMapper {

    fun mapFrom(from: ResultDomainError): RepositoryErrorModel {
        return when (from) {
            is ResultDomainError.GenericError -> RepositoryErrorModel.GenericError
            is ResultDomainError.NetworkError -> {
                if (from.httpCode == HTTP_CODE_FORBIDDEN) {
                    RepositoryErrorModel.MaxOfRequestReach
                } else {
                    RepositoryErrorModel.GenericError
                }
            }
            ResultDomainError.UnknownError -> RepositoryErrorModel.GenericError
        }
    }
}
