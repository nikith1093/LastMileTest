package com.example.lastmiletest.mvvm.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.lastmiletest.base.BaseViewModel
import com.example.lastmiletest.base.mappings.UseCaseResult
import com.example.lastmiletest.domain.usecase.PopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: PopularMoviesUseCase,
) :
    BaseViewModel<HomeViewModelIntent, HomeViewModelState>() {

    override fun sendIntent(intent: HomeViewModelIntent) {
        when (intent) {
            is HomeViewModelIntent.LoadPopularMovies -> {
                getPopularMovies(intent.page)
            }
        }
    }

    private fun getPopularMovies(page: Int) {
        state.value = HomeViewModelState.Loading
        viewModelScope.launch {
            when (val result = useCase.execute(page)) {
                is UseCaseResult.Success -> {
                    state.value = HomeViewModelState.GetPopularMovies(result.data)
                }
                is UseCaseResult.Error -> {
                    state.value = HomeViewModelState.ErrorOccurred
                }
            }
        }
    }
}