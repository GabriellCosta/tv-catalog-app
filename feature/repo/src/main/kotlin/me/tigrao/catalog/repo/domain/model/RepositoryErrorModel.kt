package me.tigrao.catalog.repo.domain.model

internal sealed interface RepositoryErrorModel {

    object GenericError : RepositoryErrorModel

    object MaxOfRequestReach : RepositoryErrorModel
}
