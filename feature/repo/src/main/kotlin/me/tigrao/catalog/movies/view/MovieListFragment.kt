package me.tigrao.catalog.movies.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.tigrao.catalog.infra.action.dispatcher.ViewAction
import me.tigrao.catalog.movies.R
import me.tigrao.catalog.movies.databinding.FragmentMovieListBinding
import me.tigrao.catalog.movies.presentation.RepoViewModel
import me.tigrao.catalog.movies.presentation.model.RepoAction
import me.tigrao.catalog.movies.presentation.model.RepoEvent
import me.tigrao.catalog.movies.presentation.model.RepoSate
import me.tigrao.catalog.movies.view.adapter.LayoutManagerFactory
import me.tigrao.catalog.movies.view.adapter.RepoAdapter
import me.tigrao.catalog.movies.view.adapter.RepoLoadStateAdapter
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewActionDispatcher
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list), StateViewActionDispatcher {

    private val binder by viewBinding(FragmentMovieListBinding::bind)

    private val viewModel: RepoViewModel by viewModel()
    private val repoAdapter by inject<RepoAdapter>()
    private val layoutMangerFactory by inject<LayoutManagerFactory>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)

        binder.state.stateViewDispatchAction = this
        prepareList()
        prepareObservers()
        preparePagerCollect()
        prepareStateFlowRead()
    }

    private fun preparePagerCollect() {
        lifecycleScope.launch {
            viewModel.reposPager.collectLatest { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
    }

    private fun prepareStateFlowRead() {
        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collect {
                viewModel.dispatch(
                    RepoAction.CollectState(it, repoAdapter.itemCount)
                )
            }
        }
    }

    private fun prepareObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::readState)

        viewModel.event.observe(viewLifecycleOwner, ::readEvent)
    }

    private fun readState(state: RepoSate) = when (state) {
        is RepoSate.EmptyState -> onEmptyState(state)
        RepoSate.SuccessState -> onSuccessState()
    }

    private fun readEvent(event: RepoEvent) = when (event) {
        RepoEvent.TryAgain -> tryAgain()
    }

    private fun tryAgain() {
        repoAdapter.retry()

        //TODO: Should I move this to the VM
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

    private fun onEmptyState(state: RepoSate.EmptyState) {
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
        viewModel.dispatch(action as RepoAction)
    }
}
