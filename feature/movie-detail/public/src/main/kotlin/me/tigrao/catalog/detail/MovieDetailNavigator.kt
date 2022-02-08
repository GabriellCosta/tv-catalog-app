package me.tigrao.catalog.detail

import me.tigrao.catalog.detail.view.MovieDetailArgs

interface MovieDetailNavigator {
    fun openMovieDetail(args: MovieDetailArgs)
}
