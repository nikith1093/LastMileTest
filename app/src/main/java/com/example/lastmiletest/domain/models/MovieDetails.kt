package com.example.lastmiletest.domain.models

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("genres") val genres: List<Genre>,
)

data class Genre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
