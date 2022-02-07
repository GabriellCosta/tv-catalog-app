package me.tigrao.catalog.detail.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class MovieDetailArgs(
    val id: Long,
    val name: String,
    val image: String,
    val genreList: List<String>,
    val summary: String,
) : Parcelable
