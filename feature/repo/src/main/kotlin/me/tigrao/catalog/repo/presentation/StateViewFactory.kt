package me.tigrao.catalog.repo.presentation

import android.content.res.Resources
import me.tigrao.tv.catalog.designsystem.viewstate.ButtonViewArg
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewArg
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewType
import me.tigrao.catalog.repo.R
import me.tigrao.catalog.repo.presentation.model.RepoAction

internal class StateViewFactory(
    private val resources: Resources
) {

    fun emptyState() = StateViewArg(
        type = StateViewType.Empty(),
        title = resources.getString(R.string.repositories_state_empty_title),
        positiveButton = ButtonViewArg(
            text = resources.getString(R.string.repositories_state_empty_button_positive),
            action = RepoAction.TryAgain
        )
    )

    fun genericError() =
        StateViewArg(
            type = StateViewType.Empty(),
            title = resources.getString(R.string.repositories_state_generic_title),
            positiveButton = ButtonViewArg(
                text = resources.getString(R.string.repositories_state_generic_button_positive),
                action = RepoAction.TryAgain
            )
        )
}
