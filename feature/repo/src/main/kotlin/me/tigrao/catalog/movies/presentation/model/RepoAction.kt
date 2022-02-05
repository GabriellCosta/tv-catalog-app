package me.tigrao.catalog.movies.presentation.model

import androidx.paging.CombinedLoadStates
import me.tigrao.catalog.infra.action.dispatcher.ViewAction

internal sealed interface RepoAction : ViewAction {

    data class CollectState(val state: CombinedLoadStates, val itemCount: Int) : RepoAction

    object TryAgain : RepoAction
}
