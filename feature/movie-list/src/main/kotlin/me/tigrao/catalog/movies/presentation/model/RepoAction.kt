package me.tigrao.catalog.movies.presentation.model

import androidx.paging.CombinedLoadStates
import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.infra.action.dispatcher.ViewAction

internal sealed interface RepoAction : ViewAction {

    data class CollectState(val state: CombinedLoadStates, val itemCount: Int) : RepoAction

    object TryAgain : RepoAction

    data class SearchInput(val query: String) : RepoAction

    data class OpenDetail(val data: MovieDetailArgs) : RepoAction
}
