package me.tigrao.catalog.movies.domain

import deb.tigrao.catalog.infra.api.ResultDomainError
import me.tigrao.catalog.movies.domain.mapper.FetchMovieListErrorMapper
import me.tigrao.catalog.movies.domain.model.MovieListErrorModel
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchRepositoryErrorMapperTest {

    private val subject = FetchMovieListErrorMapper()

    @Test
    fun mapFrom_unknownError_returnGenericError() {
        val result = subject.mapFrom(
            from = ResultDomainError.UnknownError
        )

        val expected = MovieListErrorModel.GenericError

        assertEquals(expected, result)
    }

    @Test
    fun mapFrom_networkError_returnGenericError() {
        val result = subject.mapFrom(
            from = ResultDomainError.NetworkError(
                httpCode = 300,
                exceptionTitle = "mock-title",
                httpMessage = "message",
                localizeMessage = "localized message",
                isConnectionError = false,
            )
        )

        val expected = MovieListErrorModel.GenericError

        assertEquals(expected, result)
    }
}
