package com.example.lastmiletest.domain.usecase

import com.example.lastmiletest.base.mappings.UseCaseResult
import com.example.lastmiletest.base.mappings.toUseCaseResult
import com.example.lastmiletest.domain.models.Movie
import com.example.lastmiletest.domain.models.PaginationResponse
import com.example.lastmiletest.domain.repository.MovieRepository
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    suspend fun execute(page: Int): UseCaseResult<PaginationResponse<Movie>> {
        return repository.getPopularMovies(page).toUseCaseResult()
    }
}