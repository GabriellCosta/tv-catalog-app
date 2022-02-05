package me.tigrao.catalog.repo.presentation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.tigrao.catalog.repo.data.RepoDataSource

private const val PAGE_SIZE = 20

internal class PagerProvider(
    private val repoDataSource: RepoDataSource,
) {

    fun providePager() = Pager(
        PagingConfig(pageSize = PAGE_SIZE),
    ) {
        repoDataSource
    }.flow
}
