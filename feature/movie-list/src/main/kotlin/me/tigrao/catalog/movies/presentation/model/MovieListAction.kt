package me.tigrao.catalog.movies.presentation.model

import androidx.paging.CombinedLoadStates
import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.infra.action.dispatcher.ViewAction

internal sealed interface MovieListAction : ViewAction {

    data class CollectState(val state: CombinedLoadStates, val itemCount: Int) : MovieListAction

    object TryAgain : MovieListAction

    data class SearchInput(val query: String) : MovieListAction

    data class OpenDetail(val data: MovieDetailArgs) : MovieListAction
}
