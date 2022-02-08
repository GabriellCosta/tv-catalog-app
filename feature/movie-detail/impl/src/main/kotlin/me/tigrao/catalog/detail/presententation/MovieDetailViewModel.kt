package me.tigrao.catalog.detail.presententation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.tigrao.catalog.detail.domain.FetchEpisodesListUseCase
import me.tigrao.catalog.detail.domain.model.EpisodeListErrorModel
import me.tigrao.catalog.detail.presententation.model.MovieDetailAction
import me.tigrao.catalog.detail.presententation.model.MovieDetailState
import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI
import me.tigrao.catalog.detail.presententation.model.data.MovieDetailListItemType
import me.tigrao.catalog.detail.presententation.model.data.SeasonModelUi
import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.infra.action.dispatcher.ActionDispatcher
import me.tigrao.catalog.infra.key.getArgs

internal class MovieDetailViewModel(
    handle: SavedStateHandle,
    private val fetchEpisodesListUseCase: FetchEpisodesListUseCase,
    private val stateViewFactory: MovieDetailStateViewFactory,
) : ViewModel(), ActionDispatcher<MovieDetailAction> {

    private val arg: MovieDetailArgs = handle.getArgs()

    private val _state = MutableLiveData<MovieDetailState>()
    val state: LiveData<MovieDetailState> = _state

    init {
        _state.value = MovieDetailState.InitialState(arg)
        fetchEpisodeList()
    }

    override fun dispatch(action: MovieDetailAction) {
        when (action) {
            is MovieDetailAction.EpisodeClickAction -> TODO()
            MovieDetailAction.TryAgain -> tryAgain()
        }
    }

    private fun tryAgain() {
        fetchEpisodeList()
    }

    private fun fetchEpisodeList() {
        viewModelScope.launch {
            _state.postValue(MovieDetailState.LoadState)

            fetchEpisodesListUseCase(arg.id)
                .onSuccess {
                    val result = mutableListOf<MovieDetailListItemType>()

                    it.data.forEach { model ->
                        result.add(
                            SeasonModelUi(
                                name = model.season,
                            )
                        )

                        result.addAll(model.episodes.map { episodesModel ->
                            EpisodeModelUI(
                                name = episodesModel.name,
                                summary = episodesModel.summary,
                                info = "Season: ${episodesModel.season} | Ep number: #${episodesModel.number}",
                                image = episodesModel.image,
                            )
                        })
                    }

                    _state.postValue(
                        MovieDetailState.Success(
                            result
                        )
                    )
                }
                .onError {
                    val state = when (it) {
                        EpisodeListErrorModel.EmptyEpisodes -> stateViewFactory.emptyState()
                        EpisodeListErrorModel.GenericError -> stateViewFactory.genericError()
                    }
                    _state.postValue(MovieDetailState.Error(state))
                }
        }
    }
}
