package me.tigrao.catalog.detail.data

import deb.tigrao.catalog.infra.api.callApi

internal class MovieDetailDataSource(
    private val api: MovieDetailApi,
) {

    suspend fun fetchEpisodesList(id: Long) =
        callApi {
            api.getSeasons(id)
        }
}
