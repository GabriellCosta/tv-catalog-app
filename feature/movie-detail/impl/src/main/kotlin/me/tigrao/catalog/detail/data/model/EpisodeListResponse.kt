package me.tigrao.catalog.detail.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class EpisodeListResponse(
    val id: Long,
    val number: Int,
    val name: String,
    val season: Int,
    val image: ImageResponse
)

//TODO: THIS ONE IS DUPLICATED
@JsonClass(generateAdapter = true)
internal data class ImageResponse(
    val medium: String,
    val original: String,
)
