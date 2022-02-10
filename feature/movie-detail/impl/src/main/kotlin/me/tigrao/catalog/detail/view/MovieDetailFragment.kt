package me.tigrao.catalog.detail.view

import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import me.tigrao.catalog.detail.impl.R
import me.tigrao.catalog.detail.impl.databinding.FragmentMovieDetailBinding
import me.tigrao.catalog.detail.presententation.MovieDetailViewModel
import me.tigrao.catalog.detail.presententation.model.MovieDetailAction
import me.tigrao.catalog.detail.presententation.model.MovieDetailState
import me.tigrao.catalog.detail.view.adapter.EpisodeListAdapter
import me.tigrao.catalog.infra.action.dispatcher.ViewAction
import me.tigrao.catalog.infra.key.viewModelState
import me.tigrao.tv.catalog.designsystem.viewstate.StateViewActionDispatcher

internal class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail),
    StateViewActionDispatcher {

    private val binder by viewBinding(FragmentMovieDetailBinding::bind)

    private val viewModel by viewModelState<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareObservers()
        binder.state.stateViewDispatchAction = this
    }

    private fun prepareObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::readState)
    }

    private fun readState(states: MovieDetailState) = when (states) {
        is MovieDetailState.InitialState -> onInitialState(states)
        MovieDetailState.LoadState -> onLoadState()
        is MovieDetailState.Success -> onSuccess(states)
        is MovieDetailState.Error -> onErrorState(states)
    }

    private fun onErrorState(detailState: MovieDetailState.Error) {
        with(binder) {
            progress.isVisible = false
            state.isVisible = true
            state.prepareLayout(detailState.state)
        }
    }

    private fun onInitialState(state: MovieDetailState.InitialState) {
        with(binder) {
            title.text = state.data.name
            genre.text = state.data.genreList.toString()

            //TODO: THIS SHOULD be done on the ViewModel
            if (state.data.schedule.weekDay.isNotEmpty()) {
                schedule.text = getString(
                    R.string.movie_detail_schedule,
                    state.data.schedule.time,
                    state.data.schedule.weekDay
                        .toString()
                        .replace("[", "")
                        .replace("]", "")
                )
                schedule.isVisible = true
            } else {
                schedule.isVisible = false
            }


            summary.text =
                HtmlCompat.fromHtml(state.data.summary, HtmlCompat.FROM_HTML_MODE_COMPACT)

            Glide.with(requireContext())
                .load(state.data.image)
                .into(posterImage)
        }
    }

    private fun onSuccess(state: MovieDetailState.Success) {
        with(binder) {
            progress.isVisible = false

            recycler.isVisible = true
            recycler.adapter = EpisodeListAdapter(state.data)
            recycler.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onLoadState() {
        binder.progress.isVisible = true
        binder.state.isVisible = false
    }

    override fun dispatch(action: ViewAction) {
        viewModel.dispatch(action as MovieDetailAction)
    }
}
