package me.tigrao.catalog.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.tigrao.catalog.movies.databinding.ItemListBinding
import me.tigrao.catalog.movies.domain.model.MovieListDataModel
import me.tigrao.catalog.movies.presentation.model.MovieListAction

internal class RepoViewHolder(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        operator fun invoke(parent: ViewGroup): RepoViewHolder {
            val binding =
                ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            return RepoViewHolder(binding)
        }
    }

    fun bind(item: MovieListDataModel, clickListener: (action: MovieListAction) -> Unit) {
        binding.title.text = item.title
        binding.description.text =
            HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.author.text = item.author

        with(binding.avatar) {
            Glide.with(this)
                .load(item.image)
                .apply(RequestOptions.circleCropTransform())
                .into(this)
        }

        binding.root.setOnClickListener {
            clickListener(item.action)
        }
    }
}
