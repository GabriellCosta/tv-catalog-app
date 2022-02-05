package me.tigrao.catalog.movies.presentation.model

import me.tigrao.catalog.infra.action.dispatcher.ViewAction

internal sealed interface RepoEvent: ViewAction {
    
    object TryAgain: RepoEvent
}
