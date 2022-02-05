package me.tigrao.catalog.movies.data.api

import me.tigrao.catalog.movies.data.api.model.MovieListItemResponse
import me.tigrao.catalog.movies.data.api.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieListApi {

    @GET("shows")
    suspend fun fetchMovieList(
        @Query("page") page: Int
    ): List<MovieListItemResponse>
}
