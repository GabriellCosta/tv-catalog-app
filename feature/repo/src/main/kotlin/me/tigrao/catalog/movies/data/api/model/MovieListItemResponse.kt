package me.tigrao.catalog.movies.data.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MovieListItemResponse(
    val id: Long,
    val name: String,
    val image: ImageResponse,
    val summary: String,
    val schedule : ScheduleResponse,
    val genres: List<String>,
    val status: String,
)

@JsonClass(generateAdapter = true)
internal data class ScheduleResponse(
    val time: String,
    val days: List<String>,
)

@JsonClass(generateAdapter = true)
internal data class ImageResponse(
    val medium: String,
    val original: String,
)
