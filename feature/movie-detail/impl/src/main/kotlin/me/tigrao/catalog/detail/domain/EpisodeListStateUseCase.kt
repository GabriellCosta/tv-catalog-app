package me.tigrao.catalog.detail.domain

import me.tigrao.catalog.detail.domain.model.EpisodeListErrorModel
import me.tigrao.catalog.detail.presententation.MovieDetailStateViewFactory
import me.tigrao.catalog.detail.presententation.mapper.EpisodeModelToModelUiMapper
import me.tigrao.catalog.detail.presententation.model.MovieDetailState

internal interface EpisodeListStateUseCase {
    suspend operator fun invoke(id: Long): MovieDetailState
}

internal class EpisodeListStateUseCaseDefault(
    private val fetchEpisodesListUseCase: FetchEpisodesListUseCase,
    private val stateViewFactory: MovieDetailStateViewFactory,
    private val successMapper: EpisodeModelToModelUiMapper,
) : EpisodeListStateUseCase {
    override suspend fun invoke(id: Long): MovieDetailState {
        return fetchEpisodesListUseCase(id)
            .map(
                success = {
                    val mappedValue = successMapper.mapFrom(it)
                    MovieDetailState.Success(mappedValue)
                },
                error = {
                    val mappedValue = when (it) {
                        EpisodeListErrorModel.EmptyEpisodes -> stateViewFactory.emptyState()
                        EpisodeListErrorModel.GenericError -> stateViewFactory.genericError()
                    }

                    MovieDetailState.Error(mappedValue)
                }
            )
    }
}
