package me.tigrao.catalog.detail.domain

import deb.tigrao.catalog.infra.api.ResultDomain
import me.tigrao.catalog.detail.data.MovieDetailDataSource
import me.tigrao.catalog.detail.domain.mapper.EpisodeListSuccessMapper
import me.tigrao.catalog.detail.domain.mapper.EpisodesListErrorMapper
import me.tigrao.catalog.detail.domain.model.EpisodeListErrorModel
import me.tigrao.catalog.detail.domain.model.EpisodeListModel

internal interface FetchEpisodesListUseCase {
    suspend operator fun invoke(id: Long): ResultDomain<EpisodeListModel, EpisodeListErrorModel>
}

internal class FetchEpisodesListUseCaseDefault(
    private val dataSource: MovieDetailDataSource,
    private val success: EpisodeListSuccessMapper,
    private val error: EpisodesListErrorMapper,
) : FetchEpisodesListUseCase {
    override suspend fun invoke(id: Long): ResultDomain<EpisodeListModel, EpisodeListErrorModel> {
        return dataSource.fetchEpisodesList(id)
            .transformMap(
                success = success::mapFrom,
                error = error::mapFrom,
            )
    }
}
