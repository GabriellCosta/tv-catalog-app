package me.tigrao.catalog.movies.presentation.model

import androidx.paging.PagingData
import me.tigrao.catalog.movies.domain.model.MovieListDataModel
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewArg

internal sealed interface RepoSate {

    data class EmptyState(val state: StateViewArg) : RepoSate

    object SuccessState : RepoSate

    data class DataLoaded(val data: PagingData<MovieListDataModel>): RepoSate
}
