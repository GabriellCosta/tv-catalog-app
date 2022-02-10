package me.tigrao.catalog.detail.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailArgs(
    val id: Long,
    val name: String,
    val image: String,
    val schedule: ScheduleArgs,
    val genreList: List<String>,
    val summary: String,
) : Parcelable

@Parcelize
data class ScheduleArgs(
    val time: String,
    val weekDay: List<String>,
) : Parcelable
