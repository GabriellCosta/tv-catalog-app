package me.tigrao.catalog.detail.presententation.model

import me.tigrao.catalog.detail.presententation.model.data.MovieDetailListItemType
import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewArg

internal sealed interface MovieDetailState {

    // TODO: Should be a different type and not the same from navigator
    data class InitialState(val data: MovieDetailArgs) : MovieDetailState

    object LoadState : MovieDetailState

    data class Success(val data: List<MovieDetailListItemType>) : MovieDetailState

    data class Error(val state: StateViewArg) : MovieDetailState
}
