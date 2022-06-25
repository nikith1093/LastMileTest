package com.example.lastmiletest.mvvm.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastmiletest.R
import com.example.lastmiletest.base.PaginationScrollListener
import com.example.lastmiletest.base.binding.viewBinding
import com.example.lastmiletest.databinding.FragmentHomeBinding
import com.example.lastmiletest.domain.models.Movie
import com.example.lastmiletest.domain.models.PaginationResponse
import com.example.lastmiletest.mvvm.home.adapter.MovieAdapter
import com.example.lastmiletest.mvvm.home.viewmodel.HomeViewModel
import com.example.lastmiletest.mvvm.home.viewmodel.HomeViewModelIntent
import com.example.lastmiletest.mvvm.home.viewmodel.HomeViewModelState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var movieAdapter: MovieAdapter
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var currentPage = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.sendIntent(HomeViewModelIntent.LoadPopularMovies(currentPage))
        initRecyclerview()
        observeStates()
    }

    private fun initRecyclerview() {
        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        movieAdapter = MovieAdapter(requireContext()) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                    movieId = it.id
                )
            )
        }
        binding.recyclerviewMovies.apply {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    movieAdapter.addLoading(true)
                    currentPage++
                    viewModel.sendIntent(HomeViewModelIntent.LoadPopularMovies(currentPage))
                }
            })
        }
    }

    private fun observeStates() {
        viewModel.getState().observe(viewLifecycleOwner) {
            when (it) {
                is HomeViewModelState.GetPopularMovies -> {
                    setUpListData(it.data)
                }
                HomeViewModelState.ErrorOccurred -> {
                }
                HomeViewModelState.Loading -> {
                    if (currentPage == 1) {
                        with(binding) {
                            recyclerviewMovies.visibility = View.GONE
                            progressCircular.visibility = View.VISIBLE
                        }
                    }
                }
                is HomeViewModelState.NavigateToMovieDetails -> {
                }
            }
        }
    }

    private fun setUpListData(response: PaginationResponse<Movie>) {
        if (currentPage == 1) {
            with(binding) {
                recyclerviewMovies.visibility = View.VISIBLE
                progressCircular.visibility = View.GONE
            }
        }
        if (response.totalPages < currentPage) {
            isLastPage = true
        }
        isLoading = false
        movieAdapter.addData(response.results)
    }
}