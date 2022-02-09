package me.tigrao.catalog.movies.domain

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FetchRepositoryUseCaseImplTest {

    private val noSearchUseCase = mockk<FetchMovieListWithoutSearchUseCase>(relaxed = true)
    private val withSearchUseCase = mockk<FetchMovieListSearchUseCase>(relaxed = true)

    private val subject = FetchMovieListUseCaseImpl(withSearchUseCase, noSearchUseCase)

    @Test
    fun invoke_withQueryParameter_callSuccessMapper() = runBlocking {
        val parameters = FetchMovieListParameters(query = "query", page = 1)

        subject(parameters)

        coVerify(exactly = 1) {
            withSearchUseCase(parameters)
        }
    }

    @Test
    fun invoke_withoutQueryParameter_callSuccessMapper() = runBlocking {
        val parameters = FetchMovieListParameters(query = "", page = 1)

        subject(parameters)

        coVerify(exactly = 1) {
            noSearchUseCase(parameters)
        }
    }
}
