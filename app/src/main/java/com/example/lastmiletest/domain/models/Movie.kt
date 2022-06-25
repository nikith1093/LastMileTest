package com.example.lastmiletest.domain.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
)

fun String.getPosterPath(): String {
    return "https://image.tmdb.org/t/p/w200".plus(this)
}
