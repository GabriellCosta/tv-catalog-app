package me.tigrao.catalog.detail.presententation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.tigrao.catalog.detail.domain.FetchEpisodesListUseCase
import me.tigrao.catalog.detail.presententation.model.MovieDetailAction
import me.tigrao.catalog.detail.presententation.model.MovieDetailState
import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.infra.action.dispatcher.ActionDispatcher
import me.tigrao.catalog.infra.key.getArgs

internal class MovieDetailViewModel(
    handle: SavedStateHandle,
    private val fetchEpisodesListUseCase: FetchEpisodesListUseCase,
) : ViewModel(), ActionDispatcher<MovieDetailAction> {

    private val arg: MovieDetailArgs = handle.getArgs()

    private val _state = MutableLiveData<MovieDetailState>()
    val state: LiveData<MovieDetailState> = _state

    override fun dispatch(action: MovieDetailAction) {
        TODO("Not yet implemented")
    }

    private fun fetchEpisodeList() {
        viewModelScope.launch {
            fetchEpisodesListUseCase(arg.id)
                .onSuccess {

                }
                .onError {

                }
        }
    }
}
