package me.tigrao.catalog.detail.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import me.tigrao.catalog.detail.R
import me.tigrao.catalog.detail.databinding.FragmentMovieDetailBinding

internal class MovieDetailFragment: Fragment(R.layout.fragment_movie_detail) {

    private val binder by viewBinding(FragmentMovieDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
