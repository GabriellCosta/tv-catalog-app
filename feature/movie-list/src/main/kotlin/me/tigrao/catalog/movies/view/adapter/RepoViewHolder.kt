package me.tigrao.catalog.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.tigrao.catalog.movies.R
import me.tigrao.catalog.movies.databinding.ItemListRepoBinding
import me.tigrao.catalog.movies.domain.model.MovieListDataModel

internal class RepoViewHolder(private val binding: ItemListRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        operator fun invoke(parent: ViewGroup): RepoViewHolder {
            val binding =
                ItemListRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            return RepoViewHolder(binding)
        }
    }

    fun bind(item: MovieListDataModel) {
        binding.title.text = item.title
        binding.description.text =
            HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.author.text = item.author

        with(binding.avatar) {
            Glide.with(this)
                .load(item.image)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_git)
                .into(this)
        }
    }
}
