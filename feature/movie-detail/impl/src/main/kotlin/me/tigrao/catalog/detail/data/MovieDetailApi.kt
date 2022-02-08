package me.tigrao.catalog.detail.data

import me.tigrao.catalog.detail.data.model.EpisodeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MovieDetailApi {

    @GET("shows/{id}/episodes")
    suspend fun getSeasons(@Path("id") id: Long): List<EpisodeListResponse>
}
