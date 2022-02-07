package me.tigrao.catalog.movies.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class MovieListResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    val items: List<MovieListItemResponse>,
)
