package me.tigrao.catalog.detail.domain.mapper

import deb.tigrao.catalog.infra.api.ResultDomainError
import me.tigrao.catalog.detail.domain.model.EpisodeListErrorModel

internal class EpisodesListErrorMapper {

    fun mapFrom(from: ResultDomainError): EpisodeListErrorModel {
        return when (from) {
            else -> EpisodeListErrorModel.GenericError
        }
    }
}
