package me.tigrao.catalog.detail.presententation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.tigrao.catalog.detail.domain.EpisodeListStateUseCase
import me.tigrao.catalog.detail.presententation.model.MovieDetailAction
import me.tigrao.catalog.detail.presententation.model.MovieDetailState
import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.infra.action.dispatcher.ActionDispatcher
import me.tigrao.catalog.infra.key.getArgs

internal class MovieDetailViewModel(
    handle: SavedStateHandle,
    private val episodeListStateUseCase: EpisodeListStateUseCase
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

            _state.postValue(
                episodeListStateUseCase(arg.id)
            )
        }
    }
}
