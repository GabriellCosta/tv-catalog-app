package me.tigrao.catalog.repo.presentation.model

import me.tigrao.tv.catalog.designsystem.viewstate.StateViewArg

internal sealed interface RepoSate {

    data class EmptyState(val state: StateViewArg) : RepoSate

    object SuccessState : RepoSate
}
