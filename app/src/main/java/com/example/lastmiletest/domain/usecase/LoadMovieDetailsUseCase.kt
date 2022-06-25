package com.example.lastmiletest.domain.usecase

import com.example.lastmiletest.base.mappings.UseCaseResult
import com.example.lastmiletest.base.mappings.toUseCaseResult
import com.example.lastmiletest.domain.models.MovieDetails
import com.example.lastmiletest.domain.repository.MovieRepository
import javax.inject.Inject

class LoadMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    suspend fun execute(id: Int): UseCaseResult<MovieDetails> {
        return repository.getMovieDetails(id).toUseCaseResult()
    }
}