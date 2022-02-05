package me.tigrao.catalog.infra.action.dispatcher

interface ActionDispatcher<A : ViewAction> {
    fun dispatch(action: A)
}
