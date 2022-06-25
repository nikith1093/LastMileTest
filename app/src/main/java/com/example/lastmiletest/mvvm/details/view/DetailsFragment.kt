package com.example.lastmiletest.mvvm.details.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.lastmiletest.R
import com.example.lastmiletest.base.binding.viewBinding
import com.example.lastmiletest.databinding.FragmentDetailsBinding
import com.example.lastmiletest.domain.models.MovieDetails
import com.example.lastmiletest.domain.models.getPosterPath
import com.example.lastmiletest.mvvm.details.viewmodel.DetailsViewModel
import com.example.lastmiletest.mvvm.details.viewmodel.DetailsViewModelIntent
import com.example.lastmiletest.mvvm.details.viewmodel.DetailsViewModelState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val binding by viewBinding { FragmentDetailsBinding.bind(it) }

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStates()
        arguments?.let {
            DetailsFragmentArgs.fromBundle(it).apply {
                viewModel.sendIntent(
                    DetailsViewModelIntent.LoadMovieDetails(movieId)
                )
            }
        }
    }

    private fun observeStates() {
        viewModel.getState().observe(viewLifecycleOwner) {
            when (it) {
                is DetailsViewModelState.GetMovieDetails -> {
                    loadMovieDetailsWithUI(it.data)
                }
                DetailsViewModelState.ErrorOccurred -> {
                }
                DetailsViewModelState.Loading -> {
                }
            }
        }
    }

    private fun loadMovieDetailsWithUI(movieDetails: MovieDetails) {
        with(binding) {
            Glide.with(requireContext()).load(movieDetails.posterPath.getPosterPath())
                .into(imageMovie)
            textMovieName.text = movieDetails.originalTitle
            textMovieOverview.text = getString(R.string.overview, movieDetails.overview)
            textMovieRuntime.text = getString(R.string.runtime, movieDetails.runtime)
            textMovieGenre.text =
                getString(R.string.genre, movieDetails.genres.joinToString(separator = ",") {
                    it.name
                })
        }
    }
}