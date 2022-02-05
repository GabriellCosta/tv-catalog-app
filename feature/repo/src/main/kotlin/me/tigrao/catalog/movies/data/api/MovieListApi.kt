package me.tigrao.catalog.movies.data.api

import me.tigrao.catalog.movies.data.api.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieListApi {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun fetchMovieList(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): MovieListResponse
}
