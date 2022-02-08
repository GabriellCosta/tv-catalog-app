package me.tigrao.catalog.movies.presentation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.tigrao.catalog.movies.data.MovieListPageDatasourceFactory

private const val PAGE_SIZE = 20

internal class PagerProvider(
    private val movieListPageDataSource: MovieListPageDatasourceFactory,
) {

    fun providePager(query: String = "") = Pager(
        PagingConfig(pageSize = PAGE_SIZE),
    ) {
        movieListPageDataSource.getInstance(query)
    }.flow
}
