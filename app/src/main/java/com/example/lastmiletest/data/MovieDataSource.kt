package com.example.lastmiletest.data

import com.example.lastmiletest.base.callApi
import com.example.lastmiletest.network.MovieApi
import javax.inject.Inject
import javax.inject.Named

class MovieDataSource @Inject constructor(
    @Named("api_key") private val apiKey: String,
    private val apiService: MovieApi,
) {

    suspend fun getPopularMovies(page: Int) = callApi {
        apiService.getPopularMovies(apiKey, page)
    }

    suspend fun getMovieDetails(id: Int) = callApi {
        apiService.getMovieDetails(id, apiKey)
    }
}
