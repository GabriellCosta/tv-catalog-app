package me.tigrao.catalog.detail.presententation.model

import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI
import me.tigrao.catalog.detail.presententation.model.data.InitialStateModelUi

internal sealed interface MovieDetailState {

    data class InitialState(val data: InitialStateModelUi) : MovieDetailState

    object LoadState : MovieDetailState

    data class Success(val data: List<EpisodeModelUI>) : MovieDetailState
}
