package com.example.lastmiletest.domain.repository

import com.example.lastmiletest.base.mappings.RepositoryResult
import com.example.lastmiletest.base.mappings.toRepositoryResult
import com.example.lastmiletest.data.MovieDataSource
import com.example.lastmiletest.domain.models.Movie
import com.example.lastmiletest.domain.models.MovieDetails
import com.example.lastmiletest.domain.models.PaginationResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val dataSource: MovieDataSource,
) {

    suspend fun getPopularMovies(page: Int): RepositoryResult<PaginationResponse<Movie>> {
        return dataSource.getPopularMovies(page).toRepositoryResult()
    }

    suspend fun getMovieDetails(id: Int): RepositoryResult<MovieDetails> {
        return dataSource.getMovieDetails(id).toRepositoryResult()
    }
}
