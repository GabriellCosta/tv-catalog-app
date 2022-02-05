package me.tigrao.tv.catalog.designsystem.viewstate

import me.tigrao.catalog.infra.action.dispatcher.ViewAction

interface StateViewActionDispatcher {
    fun dispatch(action: ViewAction)
}
