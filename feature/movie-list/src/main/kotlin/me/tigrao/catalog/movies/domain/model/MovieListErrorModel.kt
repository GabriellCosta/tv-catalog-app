package me.tigrao.catalog.movies.domain.model

internal sealed interface MovieListErrorModel {

    object GenericError : MovieListErrorModel
}
