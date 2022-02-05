package me.tigrao.catalog.movies.view

import androidx.paging.PagingData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.tigrao.tv.catalog.designsystem.viewstate.ButtonViewArg
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewArg
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewType
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import me.tigrao.catalog.movies.R
import me.tigrao.catalog.movies.domain.model.MovieListDataModel
import me.tigrao.catalog.movies.presentation.PagerProvider
import me.tigrao.catalog.movies.presentation.MoviewListViewModel
import me.tigrao.catalog.movies.presentation.StateViewFactory
import me.tigrao.catalog.movies.presentation.model.RepoAction
import me.tigrao.catalog.movies.presentation.model.RepoEvent
import me.tigrao.catalog.movies.view.adapter.LayoutManagerFactory
import me.tigrao.catalog.movies.view.adapter.MoviewListAdapter
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class RepoActivityTest {

    lateinit var koinApp: KoinApplication

    private val pagerProvider = mockk<PagerProvider>()
    private lateinit var viewModel: MoviewListViewModel

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun givenSuccessResponse_ShouldDisplayRecyclerView() {
        prepare()

        ActivityScenario.launch(MovieListFragment::class.java)

        onView(withId(R.id.rv_repo))
            .check(matches(isDisplayed()))
    }

    @Test
    @Ignore("Should mock page provider better so we can return a load state")
    fun givenInitialState_WhenLoadingRepositoryList_ShouldStartWithLoading() {
        prepare()

        ActivityScenario.launch(MovieListFragment::class.java)

        onView(withId(R.id.loading_repo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenInitialState_WhenListEmpty_ShouldShowEmptyState() {
        prepare(list = emptyList())

        ActivityScenario.launch(MovieListFragment::class.java)

        onView(withId(R.id.state))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenInitialState_WhenListEmpty_RetryShouldTriggerAction() {
        prepare(list = emptyList())

        ActivityScenario.launch(MovieListFragment::class.java)

        onView(withId(R.id.btn_view_state_action))
            .perform(click())

        assertEquals(RepoEvent.TryAgain, viewModel.event.value)
    }

    private fun createList(): List<MovieListDataModel> {
        val list = mutableListOf<MovieListDataModel>()

        repeat(10) {
            val item = MovieListDataModel(
                image = "http://google.com.br",
                title = "mock title",
                author = "authro mock",
                31,
                3333,
                description = "mock description hahah"
            )

            list.add(item)
        }

        return list
    }

    private fun prepare(
        list: List<MovieListDataModel> = createList()
    ) {
        every { pagerProvider.providePager() } returns flowOf(PagingData.from(list))

        val stateViewFactory = mockk<StateViewFactory>()
        every { stateViewFactory.emptyState() } returns StateViewArg(
            type = StateViewType.Empty(),
            title = "Unit test title",
            positiveButton = ButtonViewArg(
                text = "Button to be clicked",
                action = RepoAction.TryAgain,
            )
        )

        viewModel = MoviewListViewModel(pagerProvider, stateViewFactory)

        mockKoin()
    }

    private fun mockKoin() {
        koinApp = startKoin {}

        loadKoinModules(module {
            single {
                viewModel
            }

            single {
                MoviewListAdapter()
            }

            single {
                LayoutManagerFactory()
            }
        })
    }
}
