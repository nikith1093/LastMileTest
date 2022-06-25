package com.example.lastmiletest.network

import com.example.lastmiletest.domain.models.Movie
import com.example.lastmiletest.domain.models.MovieDetails
import com.example.lastmiletest.domain.models.PaginationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Response<PaginationResponse<Movie>>


    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetails>
}