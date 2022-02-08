package me.tigrao.catalog.detail.presententation.model

import me.tigrao.catalog.infra.action.dispatcher.ViewAction

sealed interface MovieDetailAction : ViewAction {

    object TryAgain : MovieDetailAction

    object SeasonClickAction : MovieDetailAction

    data class EpisodeClickAction(val id: Long) : MovieDetailAction
}
