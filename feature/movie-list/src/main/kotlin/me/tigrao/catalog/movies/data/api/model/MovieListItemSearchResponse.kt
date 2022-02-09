package me.tigrao.catalog.movies.data.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MovieListItemSearchResponse(
    val show: MovieListItemResponse,
)
