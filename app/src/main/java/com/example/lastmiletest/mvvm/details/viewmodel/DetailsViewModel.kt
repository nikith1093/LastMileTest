package com.example.lastmiletest.mvvm.details.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.lastmiletest.base.BaseViewModel
import com.example.lastmiletest.base.mappings.UseCaseResult
import com.example.lastmiletest.domain.usecase.LoadMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: LoadMovieDetailsUseCase,
) :
    BaseViewModel<DetailsViewModelIntent, DetailsViewModelState>() {

    override fun sendIntent(intent: DetailsViewModelIntent) {
        when (intent) {
            is DetailsViewModelIntent.LoadMovieDetails -> {
                getMovieDetails(intent.id)
            }
        }
    }

    private fun getMovieDetails(id: Int) {
        state.value = DetailsViewModelState.Loading
        viewModelScope.launch {
            when (val result = useCase.execute(id)) {
                is UseCaseResult.Success -> {
                    state.value = DetailsViewModelState.GetMovieDetails(result.data)
                }
                is UseCaseResult.Error -> {
                    state.value = DetailsViewModelState.ErrorOccurred
                }
            }
        }
    }
}