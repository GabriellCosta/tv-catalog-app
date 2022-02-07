package me.tigrao.catalog.movies.presentation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.tigrao.catalog.movies.data.RepoDataSource
import me.tigrao.catalog.movies.data.RepoDatasourceFactory

private const val PAGE_SIZE = 20

internal class PagerProvider(
    private val repoDataSource: RepoDatasourceFactory,
) {

    fun providePager(query: String = "") = Pager(
        PagingConfig(pageSize = PAGE_SIZE),
    ) {
        repoDataSource.getInstance(query)
    }.flow
}
