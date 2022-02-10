package me.tigrao.catalog.movies.domain

import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.detail.view.ScheduleArgs
import me.tigrao.catalog.movies.data.api.model.ImageResponse
import me.tigrao.catalog.movies.data.api.model.MovieListItemResponse
import me.tigrao.catalog.movies.data.api.model.ScheduleResponse
import me.tigrao.catalog.movies.domain.mapper.FetchMovieListSuccessMapper
import me.tigrao.catalog.movies.domain.model.MovieListDataModel
import me.tigrao.catalog.movies.domain.model.MovieListModel
import me.tigrao.catalog.movies.domain.model.ScheduleModel
import me.tigrao.catalog.movies.presentation.model.MovieListAction
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchMovieListSuccessMapperTest {

    private val subject = FetchMovieListSuccessMapper()

    @Test
    fun subject_withAllParameter_returnCorrect() {
        val result = subject.mapFrom(
            from = listOf(
                MovieListItemResponse(
                    id = 13,
                    name = "mock-name",
                    genres = listOf("terror", "action"),
                    summary = "summary mock",
                    image = ImageResponse(
                        medium = "medium image",
                        original = "original image",
                    ),
                    schedule = ScheduleResponse(
                        time = "22:00",
                        days = listOf("Monday", "Thursday")
                    ),
                    status = "Ended",
                )
            )
        )

        val expected = MovieListModel(
            data = listOf(
                MovieListDataModel(
                    image = "medium image",
                    title = "mock-name",
                    author = "Ended",
                    description = "summary mock",
                    schedule = ScheduleModel(
                        time = "22:00",
                        weekDay = listOf("Monday", "Thursday"),
                    ),
                    action = MovieListAction.OpenDetail(
                        data = MovieDetailArgs(
                            id = 13L,
                            name = "mock-name",
                            image = "original image",
                            genreList = listOf("terror", "action"),
                            summary = "summary mock",
                            schedule = ScheduleArgs(
                                time = "22:00",
                                weekDay = listOf("Monday", "Thursday"),
                            )
                        )
                    )
                )
            ),
        )

        assertEquals(expected, result)
    }
}
