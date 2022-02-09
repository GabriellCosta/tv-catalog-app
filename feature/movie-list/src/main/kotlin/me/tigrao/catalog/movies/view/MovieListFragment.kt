package me.tigrao.catalog.movies.view

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.tigrao.catalog.detail.MovieDetailNavigator
import me.tigrao.catalog.infra.action.dispatcher.ViewAction
import me.tigrao.catalog.movies.R
import me.tigrao.catalog.movies.databinding.FragmentMovieListBinding
import me.tigrao.catalog.movies.presentation.MovieListViewModel
import me.tigrao.catalog.movies.presentation.model.MovieListAction
import me.tigrao.catalog.movies.presentation.model.MovieListEvent
import me.tigrao.catalog.movies.presentation.model.MovieListSate
import me.tigrao.catalog.movies.view.adapter.LayoutManagerFactory
import me.tigrao.catalog.movies.view.adapter.MovieListAdapter
import me.tigrao.catalog.movies.view.adapter.RepoLoadStateAdapter
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewActionDispatcher
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val COUNT_DOWN_TIME = 1500L
private const val COUNT_DOWN_TIME_INTERVAL = 1000L

class MovieListFragment : Fragment(R.layout.fragment_movie_list), StateViewActionDispatcher {

    private val binder by viewBinding(FragmentMovieListBinding::bind)

    private val navigator by inject<MovieDetailNavigator>()
    private val viewModel: MovieListViewModel by viewModel()
    private val repoAdapter by lazy {
        MovieListAdapter {
            viewModel.dispatch(it)
        }
    }
    private val layoutMangerFactory by inject<LayoutManagerFactory>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)

        binder.state.stateViewDispatchAction = this
        prepareList()
        prepareObservers()
        prepareStateFlowRead()
        prepareSearch()
    }

    private fun prepareSearch() {
        binder.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            private var timer: CountDownTimer? = null

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                timer?.cancel()

                timer = createTimer(newText)
                return true
            }
        })
    }

    private fun createTimer(query: String?) =
        object : CountDownTimer(COUNT_DOWN_TIME, COUNT_DOWN_TIME_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                // nothing
            }

            override fun onFinish() {

                viewModel.dispatch(MovieListAction.SearchInput(query = query.orEmpty()))
            }
        }.start()

    private fun prepareStateFlowRead() {
        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collect {
                viewModel.dispatch(
                    MovieListAction.CollectState(it, repoAdapter.itemCount)
                )
            }
        }
    }

    private fun prepareObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::readState)

        viewModel.event.observe(viewLifecycleOwner, ::readEvent)
    }

    private fun readState(state: MovieListSate) = when (state) {
        is MovieListSate.EmptyState -> onEmptyState(state)
        MovieListSate.SuccessState -> onSuccessState()
        is MovieListSate.DataLoaded -> onPageLoaded(state)
    }

    private fun onPageLoaded(state: MovieListSate.DataLoaded) {
        lifecycleScope.launch {
            repoAdapter.submitData(state.data)
        }
    }

    private fun readEvent(event: MovieListEvent) = when (event) {
        MovieListEvent.TryAgain -> tryAgain()
        is MovieListEvent.OpenDetailEvent -> openDetail(event)
    }

    private fun openDetail(event: MovieListEvent.OpenDetailEvent) {
        navigator.openMovieDetail(event.data)
    }

    private fun tryAgain() {
        repoAdapter.retry()

        // TODO: Should I move this to the VM
        if (binder.state.isVisible) {
            binder.state.isVisible = false
            binder.loadingRepo.isVisible = true
        }
    }

    private fun onSuccessState() {
        binder.loadingRepo.isVisible = false
        binder.state.isVisible = false
        binder.rvRepo.isVisible = true
    }

    private fun onEmptyState(state: MovieListSate.EmptyState) {
        binder.state.isVisible = true
        binder.loadingRepo.isVisible = false

        binder.state.prepareLayout(state.state)
    }

    private fun prepareList() {
        val createLayoutManager = layoutMangerFactory.createLayoutManager(requireContext())

        with(binder.rvRepo) {
            val dividerItemDecoration = DividerItemDecoration(
                context,
                createLayoutManager.orientation
            )
            addItemDecoration(dividerItemDecoration)
            adapter = repoAdapter.withLoadStateFooter(RepoLoadStateAdapter(repoAdapter))
            layoutManager = createLayoutManager
        }
    }

    override fun dispatch(action: ViewAction) {
        viewModel.dispatch(action as MovieListAction)
    }
}
