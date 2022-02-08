package me.tigrao.catalog.movies.presentation.model

import me.tigrao.catalog.detail.view.MovieDetailArgs

internal sealed interface MovieListEvent {

    object TryAgain : MovieListEvent

    data class OpenDetailEvent(val data: MovieDetailArgs) : MovieListEvent
}
