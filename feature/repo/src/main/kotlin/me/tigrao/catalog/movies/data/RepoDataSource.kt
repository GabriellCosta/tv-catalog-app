package me.tigrao.catalog.movies.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.tigrao.catalog.movies.domain.FetchMovieListParameters
import me.tigrao.catalog.movies.domain.FetchMovieListUseCase
import me.tigrao.catalog.movies.domain.model.MovieListDataModel

internal class RepoDataSource(
    private val fetchRepositoryUseCase: FetchMovieListUseCase,
    private val repositoryErrorModelToUiMapper: RepositoryErrorModelToUiMapper,
) : PagingSource<Int, MovieListDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieListDataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListDataModel> {
        val nextPageNumber = params.key ?: 0

        val parameters = FetchMovieListParameters(
            page = nextPageNumber,
        )

        return fetchRepositoryUseCase(parameters).map(
            success = {
                LoadResult.Page(
                    data = it.data,
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber + 1
                )
            },
            error = {
                val message = repositoryErrorModelToUiMapper.mapFrom(it)

                LoadResult.Error(IllegalStateException(message))
            }
        )
    }
}
