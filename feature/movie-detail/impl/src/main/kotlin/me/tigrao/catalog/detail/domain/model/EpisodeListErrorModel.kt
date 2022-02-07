package me.tigrao.catalog.detail.domain.model

internal sealed interface EpisodeListErrorModel {

    object GenericError : EpisodeListErrorModel

    object EmptyEpisodes : EpisodeListErrorModel
}
