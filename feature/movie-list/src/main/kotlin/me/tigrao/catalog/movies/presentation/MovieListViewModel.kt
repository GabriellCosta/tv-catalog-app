package me.tigrao.catalog.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.tigrao.catalog.infra.action.dispatcher.ActionDispatcher
import me.tigrao.catalog.infra.arch.SingleLiveData
import me.tigrao.catalog.movies.presentation.model.MovieListAction
import me.tigrao.catalog.movies.presentation.model.MovieListEvent
import me.tigrao.catalog.movies.presentation.model.MovieListSate

internal class MovieListViewModel(
    private val pagerProvider: PagerProvider,
    private val stateViewFactory: StateViewFactory,
) : ViewModel(), ActionDispatcher<MovieListAction> {

    private val _state = MutableLiveData<MovieListSate>()
    val state: LiveData<MovieListSate> = _state

    private val _event = SingleLiveData<MovieListEvent>()
    val event: LiveData<MovieListEvent> = _event

    init {
        loadPageData()
    }

    override fun dispatch(action: MovieListAction) =
        when (action) {
            is MovieListAction.CollectState -> onCollectStates(action)
            MovieListAction.TryAgain -> onTryAgain()
            is MovieListAction.SearchInput -> onSearchInput(action)
            is MovieListAction.OpenDetail -> openDetail(action)
        }

    private fun openDetail(action: MovieListAction.OpenDetail) {
        _event.postValue(MovieListEvent.OpenDetailEvent(action.data))
    }

    private fun onTryAgain() {
        _event.postValue(MovieListEvent.TryAgain)
    }

    private fun onSearchInput(action: MovieListAction.SearchInput) {
        loadPageData(action.query)
    }

    private fun loadPageData(query: String = "") {
        viewModelScope.launch {
            val page = pagerProvider.providePager(query).cachedIn(viewModelScope)

            page.collectLatest { pagingData ->
                _state.postValue(MovieListSate.DataLoaded(pagingData))
            }
        }
    }

    private fun onCollectStates(action: MovieListAction.CollectState) {
        with(action.state) {
            when (this.refresh) {
                is LoadState.Error -> {
                    val state = if (contentIsEmpty(action.itemCount)) {
                        MovieListSate.EmptyState(stateViewFactory.genericError())
                    } else {
                        MovieListSate.SuccessState
                    }

                    _state.postValue(state)
                }
                is LoadState.NotLoading -> {
                    val state =
                        if (append.endOfPaginationReached && contentIsEmpty(action.itemCount)) {
                            MovieListSate.EmptyState(stateViewFactory.emptyState())
                        } else {
                            MovieListSate.SuccessState
                        }

                    _state.postValue(state)
                }
                LoadState.Loading -> {}
            }
        }
    }

    private fun contentIsEmpty(itemCount: Int) = itemCount < 1
}
