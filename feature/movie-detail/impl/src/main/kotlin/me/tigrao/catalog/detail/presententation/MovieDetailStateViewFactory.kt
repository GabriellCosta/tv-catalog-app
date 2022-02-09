package me.tigrao.catalog.detail.presententation

import android.content.res.Resources
import me.tigrao.catalog.detail.impl.R
import me.tigrao.catalog.detail.presententation.model.MovieDetailAction
import me.tigrao.tv.catalog.designsystem.viewstate.ButtonViewArg
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewArg
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewType

// TODO: COULD I MERGE BOTH STATE VIEW FACTORY
class MovieDetailStateViewFactory(
    private val resources: Resources
) {

    fun emptyState() = StateViewArg(
        type = StateViewType.Empty(),
        title = resources.getString(R.string.movie_detail_state_empty_title),
        positiveButton = ButtonViewArg(
            text = resources.getString(R.string.movie_detail_state_empty_button_positive),
            action = MovieDetailAction.TryAgain
        )
    )

    fun genericError() =
        StateViewArg(
            type = StateViewType.Empty(),
            title = resources.getString(R.string.movie_detail_state_generic_title),
            positiveButton = ButtonViewArg(
                text = resources.getString(R.string.movie_detail_state_generic_button_positive),
                action = MovieDetailAction.TryAgain
            )
        )
}
