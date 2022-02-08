package me.tigrao.catalog.movies.presentation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.tigrao.catalog.movies.data.MovieListDatasourceFactory

private const val PAGE_SIZE = 20

internal class PagerProvider(
    private val movieListDataSource: MovieListDatasourceFactory,
) {

    fun providePager(query: String = "") = Pager(
        PagingConfig(pageSize = PAGE_SIZE),
    ) {
        movieListDataSource.getInstance(query)
    }.flow
}
