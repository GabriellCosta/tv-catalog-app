package me.tigrao.catalog.repo.domain

import deb.tigrao.catalog.infra.api.ResultDomain
import deb.tigrao.catalog.infra.api.callApi
import me.tigrao.catalog.repo.data.api.RepoApi
import me.tigrao.catalog.repo.domain.model.RepositoryErrorModel
import me.tigrao.catalog.repo.domain.model.RepositoryModel

internal interface FetchRepositoryUseCase {
    suspend operator fun invoke(
        parameter: FetchRepositoryParameters,
    ): ResultDomain<RepositoryModel, RepositoryErrorModel>
}

internal class FetchRepositoryUseCaseImpl(
    private val api: RepoApi,
    private val success: FetchRepositorySuccessMapper,
    private val error: FetchRepositoryErrorMapper,
) : FetchRepositoryUseCase {
    override suspend fun invoke(
        parameter: FetchRepositoryParameters,
    ): ResultDomain<RepositoryModel, RepositoryErrorModel> {
        return callApi {
            api.fetchRepositoriesAsync(
                language = parameter.language,
                sort = parameter.sort,
                page = parameter.page,
            )
        }.transformMap(success::mapFrom, error::mapFrom)
    }
}

internal data class FetchRepositoryParameters(
    val language: String,
    val sort: String,
    val page: Int,
)
