package com.example.lastmiletest.mvvm.details.viewmodel

import com.example.lastmiletest.domain.models.MovieDetails
import com.virginmoneytest.base.ViewModelIntent
import com.virginmoneytest.base.ViewModelState

sealed class DetailsViewModelIntent : ViewModelIntent {
    class LoadMovieDetails(val id: Int) : DetailsViewModelIntent()
}

sealed class DetailsViewModelState : ViewModelState {
    object Loading : DetailsViewModelState()
    class GetMovieDetails(val data: MovieDetails) : DetailsViewModelState()
    object ErrorOccurred : DetailsViewModelState()
}