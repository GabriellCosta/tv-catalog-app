package me.tigrao.catalog.detail.presententation.model

import me.tigrao.catalog.detail.presententation.model.data.EpisodeModelUI

internal sealed interface MovieDetailState {

    data class InitialState(): MovieDetailState

    object LoadState : MovieDetailState

    data class Success(val data: List<EpisodeModelUI>): MovieDetailState
}
