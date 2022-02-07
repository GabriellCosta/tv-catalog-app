package me.tigrao.catalog.movies.domain

import me.tigrao.catalog.movies.data.api.model.OwnerResponse
import me.tigrao.catalog.movies.data.api.model.MovieListResponse
import me.tigrao.catalog.movies.data.api.model.MovieListItemResponse
import me.tigrao.catalog.movies.domain.model.MovieListDataModel
import me.tigrao.catalog.movies.domain.model.MovieListModel
import org.junit.Assert.*
import org.junit.Test

class FetchMovieListSuccessMapperTest {

    private val subject = FetchMovieListSuccessMapper()

    @Test
    fun subject_withAllParameter_returnCorrect() {
        val result = subject.mapFrom(
            from = MovieListResponse(
                totalCount = 1,
                items = listOf(
                    MovieListItemResponse(
                        id = 13,
                        name = "mock-name",
                        description = "mock-description",
                        owner = OwnerResponse(
                            avatar = "http://google.com",
                            login = "mock name",
                        ),
                        forks = 31,
                        stars = 445,
                    )
                ),
            )
        )

        val expected = MovieListModel(
            data = listOf(
                MovieListDataModel(
                    image = "http://google.com",
                    title = "mock-name",
                    author = "mock name",
                    stars = 445,
                    forks = 31,
                    description = "mock-description",
                )
            ),
        )

        assertEquals(expected, result)
    }

    @Test
    fun subject_withNullDescriptionParameter_returnCorrect() {
        val result = subject.mapFrom(
            from = MovieListResponse(
                totalCount = 1,
                items = listOf(
                    MovieListItemResponse(
                        id = 13,
                        name = "mock-name",
                        description = null,
                        owner = OwnerResponse(
                            avatar = "http://google.com",
                            login = "mock name",
                        ),
                        forks = 31,
                        stars = 445,
                    )
                ),
            )
        )

        val expected = MovieListModel(
            data = listOf(
                MovieListDataModel(
                    image = "http://google.com",
                    title = "mock-name",
                    author = "mock name",
                    stars = 445,
                    forks = 31,
                    description = "",
                )
            ),
        )

        assertEquals(expected, result)
    }
}
