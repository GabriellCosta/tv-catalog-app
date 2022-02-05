package me.tigrao.catalog.movies.domain

import deb.tigrao.catalog.infra.api.ResultDomain
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.tigrao.catalog.movies.data.api.MovieListApi
import me.tigrao.catalog.movies.domain.model.MovieListErrorModel
import me.tigrao.catalog.movies.domain.model.MovieListModel
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchRepositoryUseCaseImplTest {

    private val success = mockk<FetchMovieListSuccessMapper>()
    private val error = mockk<FetchMovieListErrorMapper>()
    private val api = mockk<MovieListApi>()

    private val subject = FetchMovieListUseCaseImpl(api, success, error)

    @Test
    fun invoke_errorApi_callErrorMapper() = runBlocking {
        val parameters = FetchMovieListParameters(language = "mock-language", "sort", 1)

        val repositoryErrorModel = mockk<MovieListErrorModel>()

        prepare(
            movieListErrorModel = repositoryErrorModel,
            apiSuccess = false,
        )

        val result = subject(parameters)

        val expected = ResultDomain.Error(repositoryErrorModel)

        assertEquals(expected, result)
    }

    @Test
    fun invoke_successApi_callSuccessMapper() = runBlocking {
        val parameters = FetchMovieListParameters(language = "mock-language", "sort", 1)

        val repositoryModel = mockk<MovieListModel>()

        prepare(
            movieListModel = repositoryModel,
            apiSuccess = true,
        )

        val result = subject(parameters)

        val expected = ResultDomain.Success(repositoryModel)

        assertEquals(expected, result)
    }

    private fun prepare(
        movieListErrorModel: MovieListErrorModel = mockk(),
        movieListModel: MovieListModel = mockk(),
        apiSuccess: Boolean = true,
    ) {
        every { error.mapFrom(any()) } returns movieListErrorModel
        every { success.mapFrom(any()) } returns movieListModel

        if (apiSuccess) {
            coEvery { api.fetchMovieList(any(), any(), any()) } returns mockk()
        } else {
            coEvery {
                api.fetchMovieList(
                    any(),
                    any(),
                    any()
                )
            } throws IllegalStateException()
        }
    }
}
