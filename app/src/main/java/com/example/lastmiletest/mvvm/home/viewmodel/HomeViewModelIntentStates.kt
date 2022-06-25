package com.example.lastmiletest.mvvm.home.viewmodel

import com.example.lastmiletest.domain.models.Movie
import com.example.lastmiletest.domain.models.PaginationResponse
import com.virginmoneytest.base.ViewModelIntent
import com.virginmoneytest.base.ViewModelState

sealed class HomeViewModelIntent : ViewModelIntent {
    class LoadPopularMovies(val page: Int) : HomeViewModelIntent()
}

sealed class HomeViewModelState : ViewModelState {
    object Loading : HomeViewModelState()
    class GetPopularMovies(val data: PaginationResponse<Movie>) : HomeViewModelState()
    object ErrorOccurred : HomeViewModelState()
    class NavigateToMovieDetails(val movie: Movie) : HomeViewModelState()
}