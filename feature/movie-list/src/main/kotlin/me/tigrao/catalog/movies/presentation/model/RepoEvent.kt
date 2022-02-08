package me.tigrao.catalog.movies.presentation.model

import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.infra.action.dispatcher.ViewAction

internal sealed interface RepoEvent : ViewAction {

    object TryAgain : RepoEvent

    data class OpenDetailEvent(val data: MovieDetailArgs) : RepoEvent
}
