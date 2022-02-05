package me.tigrao.catalog.movies.domain.model

internal sealed interface RepositoryErrorModel {

    object GenericError : RepositoryErrorModel

    object MaxOfRequestReach : RepositoryErrorModel
}
