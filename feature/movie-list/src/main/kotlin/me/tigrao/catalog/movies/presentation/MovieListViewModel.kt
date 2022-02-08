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
import me.tigrao.catalog.movies.presentation.model.RepoAction
import me.tigrao.catalog.movies.presentation.model.RepoEvent
import me.tigrao.catalog.movies.presentation.model.RepoSate

internal class MovieListViewModel(
    private val pagerProvider: PagerProvider,
    private val stateViewFactory: StateViewFactory,
) : ViewModel(), ActionDispatcher<RepoAction> {

    private val _state = MutableLiveData<RepoSate>()
    val state: LiveData<RepoSate> = _state

    private val _event = SingleLiveData<RepoEvent>()
    val event: LiveData<RepoEvent> = _event

    init {
        loadPageData()
    }

    override fun dispatch(action: RepoAction) =
        when (action) {
            is RepoAction.CollectState -> onCollectStates(action)
            RepoAction.TryAgain -> onTryAgain()
            is RepoAction.SearchInput -> onSearchInput(action)
            is RepoAction.OpenDetail -> openDetail(action)
        }

    private fun openDetail(action: RepoAction.OpenDetail) {
        _event.postValue(RepoEvent.OpenDetailEvent(action.data))
    }

    private fun onTryAgain() {
        _event.postValue(RepoEvent.TryAgain)
    }

    private fun onSearchInput(action: RepoAction.SearchInput) {
        loadPageData(action.query)
    }

    private fun loadPageData(query: String = "") {
        viewModelScope.launch {
            val page = pagerProvider.providePager(query).cachedIn(viewModelScope)

            page.collectLatest { pagingData ->
                _state.postValue(RepoSate.DataLoaded(pagingData))
            }
        }
    }

    private fun onCollectStates(action: RepoAction.CollectState) {
        with(action.state) {
            when (this.refresh) {
                is LoadState.Error -> {
                    val state = if (contentIsEmpty(action.itemCount)) {
                        RepoSate.EmptyState(stateViewFactory.genericError())
                    } else {
                        RepoSate.SuccessState
                    }

                    _state.postValue(state)
                }
                is LoadState.NotLoading -> {
                    val state =
                        if (append.endOfPaginationReached && contentIsEmpty(action.itemCount)) {
                            RepoSate.EmptyState(stateViewFactory.emptyState())
                        } else {
                            RepoSate.SuccessState
                        }

                    _state.postValue(state)
                }
                LoadState.Loading -> {}
            }
        }
    }

    private fun contentIsEmpty(itemCount: Int) = itemCount < 1
}
