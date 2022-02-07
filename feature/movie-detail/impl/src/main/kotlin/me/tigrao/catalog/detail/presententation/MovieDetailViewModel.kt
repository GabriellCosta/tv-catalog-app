package me.tigrao.catalog.detail.presententation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.tigrao.catalog.detail.domain.FetchEpisodesListUseCase
import me.tigrao.catalog.detail.presententation.model.MovieDetailAction
import me.tigrao.catalog.detail.presententation.model.MovieDetailState
import me.tigrao.catalog.infra.action.dispatcher.ActionDispatcher

internal class MovieDetailViewModel(
    private val fetchEpisodesListUseCase: FetchEpisodesListUseCase,
) : ViewModel(), ActionDispatcher<MovieDetailAction> {

    private val _state = MutableLiveData<MovieDetailState>()
    val state: LiveData<MovieDetailState> = _state

    init {

    }

    override fun dispatch(action: MovieDetailAction) {
        TODO("Not yet implemented")
    }

    private fun fetchEpisodeList() {
        viewModelScope.launch {
            fetchEpisodesListUseCase(13L)
                .onSuccess {

                }
                .onError {

                }
        }
    }
}
