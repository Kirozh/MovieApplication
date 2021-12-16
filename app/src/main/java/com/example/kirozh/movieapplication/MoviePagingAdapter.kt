package com.example.kirozh.movieapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kirozh.movieapplication.databinding.MovieItemBinding
import com.squareup.picasso.Picasso

/**
 * @author Kirill Ozhigin on 08.11.2021
 */
class MoviePagingAdapter :
    PagingDataAdapter<Movie, MoviePagingAdapter.ViewHolder>(DataDifferentiator) {

    class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(_movie: Movie) {
            binding.apply {
                movie = _movie
                Picasso.get()
                    .load(_movie.multimedia.src)
                    .resize(300, 300)
                    .centerCrop()
                    .into(binding.movieImage)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    companion object {
        private val DataDifferentiator = object : DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.link.url == newItem.link.url
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}