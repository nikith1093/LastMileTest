package com.example.lastmiletest.domain.models

import com.google.gson.annotations.SerializedName

data class PaginationResponse<T>(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<T>,
    @SerializedName("total_pages") val totalPages: Int,
)