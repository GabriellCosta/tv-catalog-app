package me.tigrao.catalog.detail.view

import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.tigrao.catalog.detail.impl.R
import me.tigrao.catalog.detail.impl.databinding.FragmentMovieDetailBinding
import me.tigrao.catalog.detail.presententation.MovieDetailViewModel
import me.tigrao.catalog.detail.presententation.model.MovieDetailState
import me.tigrao.catalog.infra.key.viewModelState

internal class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val binder by viewBinding(FragmentMovieDetailBinding::bind)

    private val viewModel by viewModelState<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareObservers()
    }

    private fun prepareObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::readState)
    }

    private fun readState(states: MovieDetailState) = when (states) {
        is MovieDetailState.InitialState -> onInitialState(states)
        MovieDetailState.LoadState -> onLoadState()
        is MovieDetailState.Success -> onSuccess(states)
    }

    private fun onInitialState(state: MovieDetailState.InitialState) {
        with(binder) {
            title.text = state.data.title
            genre.text = state.data.genre
            summary.text =
                HtmlCompat.fromHtml(state.data.summary, HtmlCompat.FROM_HTML_MODE_COMPACT)

            Glide.with(requireContext())
                .load(state.data.image)
                .apply(RequestOptions.circleCropTransform())
                .into(posterImage)
        }
    }

    private fun onSuccess(state: MovieDetailState.Success) {
    }

    private fun onLoadState() {
    }
}
