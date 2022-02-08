package me.tigrao.catalog.detail.domain.mapper

import me.tigrao.catalog.detail.data.model.EpisodeListResponse
import me.tigrao.catalog.detail.data.model.ImageResponse
import me.tigrao.catalog.detail.domain.model.EpisodeItemModel
import me.tigrao.catalog.detail.domain.model.EpisodeListDataModel
import me.tigrao.catalog.detail.domain.model.EpisodeListModel
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeListSuccessMapperTest {

    private val subject = EpisodeListSuccessMapper()

    @Test
    fun mapFrom_returnCorrectly() {
        val result = subject.mapFrom(
            listOf(
                EpisodeListResponse(
                    id = 13,
                    number = 1,
                    name = "mock name 1",
                    season = 2,
                    image = ImageResponse(
                        medium = "medium image",
                        original = "original image",
                    ),
                    summary = "summary mock",
                ),
                EpisodeListResponse(
                    id = 11,
                    number = 1,
                    name = "mock name 1",
                    season = 1,
                    image = ImageResponse(
                        medium = "medium image",
                        original = "original image",
                    ),
                    summary = "summary mock",
                ),
                EpisodeListResponse(
                    id = 12,
                    number = 2,
                    name = "mock name 2",
                    season = 1,
                    image = ImageResponse(
                        medium = "medium image",
                        original = "original image",
                    ),
                    summary = "summary mock",
                ),
            )
        )

        val expected = EpisodeListModel(
            data = listOf(
                EpisodeListDataModel(
                    season = "1",
                    episodes = listOf(
                        EpisodeItemModel(
                            id = 11,
                            number = 1,
                            name = "mock name 1",
                            season = "1",
                            image = "medium image",
                            summary = "summary mock",
                        ),
                        EpisodeItemModel(
                            id = 12,
                            number = 2,
                            name = "mock name 2",
                            season = "1",
                            image = "medium image",
                            summary = "summary mock",
                        )
                    )
                ),
                EpisodeListDataModel(
                    season = "2",
                    episodes = listOf(
                        EpisodeItemModel(
                            id = 13,
                            number = 1,
                            name = "mock name 1",
                            season = "2",
                            image = "medium image",
                            summary = "summary mock",
                        )
                    )
                )
            )
        )

        assertEquals(expected, result)
    }
}
