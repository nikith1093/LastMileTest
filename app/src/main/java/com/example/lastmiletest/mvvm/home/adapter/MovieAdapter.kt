package com.example.lastmiletest.mvvm.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lastmiletest.databinding.ItemMovieBinding
import com.example.lastmiletest.databinding.ItemProgressBinding
import com.example.lastmiletest.domain.models.Movie
import com.example.lastmiletest.domain.models.getPosterPath

class MovieAdapter(
    private val context: Context,
    private var movies: MutableList<Movie> = mutableListOf(),
    private val listener: (Movie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isFooterLoadingEnabled = false

    companion object {
        const val LOADING = 1
        const val ITEM = 2
    }

    fun addData(data: List<Movie>) {
        isFooterLoadingEnabled = false
        movies.addAll(data)
        notifyDataSetChanged()
    }

    fun addLoading(isLoading: Boolean) {
        isFooterLoadingEnabled = isLoading
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LOADING) {
            return LoadingViewHolder(
                ItemProgressBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
            )
        } else {
            return ItemViewHolder(
                ItemMovieBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = if (isFooterLoadingEnabled) {
        movies.size + 1
    } else {
        movies.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == movies.size && isFooterLoadingEnabled) LOADING else ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(movies[position])
        }
    }

    inner class ItemViewHolder(
        private val binding: ItemMovieBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            with(binding) {
                textName.text = item.title
                Glide.with(context).load(item.posterPath.getPosterPath()).into(imageMovie)
                root.setOnClickListener {
                    listener(movies[layoutPosition])
                }
            }
        }
    }

    inner class LoadingViewHolder(
        private val binding: ItemProgressBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
        }
    }
}