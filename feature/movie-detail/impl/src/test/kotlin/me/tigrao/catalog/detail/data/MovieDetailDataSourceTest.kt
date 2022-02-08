package me.tigrao.catalog.detail.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieDetailDataSourceTest {

    private val api = mockk<MovieDetailApi>()

    private val subject = MovieDetailDataSource(api)

    @Before
    fun setup() {
        coEvery {
            api.getSeasons(any())
        }
    }

    @Test
    fun fetchEpisodesList_verifyApiExecution() = runBlocking {
        subject.fetchEpisodesList(13)

        coVerify(exactly = 1) {
            api.getSeasons(13)
        }
    }
}
