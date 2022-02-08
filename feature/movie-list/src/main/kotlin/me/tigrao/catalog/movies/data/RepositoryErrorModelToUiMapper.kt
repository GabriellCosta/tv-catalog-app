package me.tigrao.catalog.movies.data

import android.content.res.Resources
import me.tigrao.catalog.movies.R
import me.tigrao.catalog.movies.domain.model.MovieListErrorModel

internal class RepositoryErrorModelToUiMapper(
    private val resources: Resources,
) {

    fun mapFrom(from: MovieListErrorModel): String {
        val resource = when (from) {
            MovieListErrorModel.GenericError -> R.string.movie_list_error_generic
        }

        return resources.getString(resource)
    }
}
