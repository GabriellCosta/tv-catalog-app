package me.tigrao.tv.catalog.designsystem.viewstate

import me.tigrao.catalog.infra.action.dispatcher.ViewAction

data class StateViewArg(
    val type: StateViewType,
    val title: String,
    val description: String? = null,
    val positiveButton: ButtonViewArg? = null,
    val negativeButton: ButtonViewArg? = null
)

data class ButtonViewArg(
    val text: String,
    val action: ViewAction
)
