package me.tigrao.catalog.movies.presentation

import android.content.res.Resources
import me.tigrao.tv.catalog.designsystem.viewstate.ButtonViewArg
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewArg
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewType
import me.tigrao.catalog.movies.R
import me.tigrao.catalog.movies.presentation.model.MovieListAction

internal class StateViewFactory(
    private val resources: Resources
) {

    fun emptyState() = StateViewArg(
        type = StateViewType.Empty(),
        title = resources.getString(R.string.movie_list_state_empty_title),
        positiveButton = ButtonViewArg(
            text = resources.getString(R.string.movie_list_state_empty_button_positive),
            action = MovieListAction.TryAgain
        )
    )

    fun genericError() =
        StateViewArg(
            type = StateViewType.Empty(),
            title = resources.getString(R.string.movie_list_state_generic_title),
            positiveButton = ButtonViewArg(
                text = resources.getString(R.string.movie_list_state_generic_button_positive),
                action = MovieListAction.TryAgain
            )
        )
}
