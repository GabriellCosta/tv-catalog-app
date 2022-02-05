package me.tigrao.tv.catalog.designsystem.viewstate

import dev.tigrao.catalog.designsytstem.emptystate.R

sealed interface StateViewType {

    val drawable: Int

    data class Api(override val drawable: Int = R.drawable.ill_api_error) : StateViewType

    data class Empty(override val drawable: Int = R.drawable.ill_empty) : StateViewType
}
