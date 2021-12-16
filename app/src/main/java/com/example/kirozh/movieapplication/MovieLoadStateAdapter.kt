package com.example.kirozh.movieapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kirozh.movieapplication.databinding.PagingHeaderBinding

/**
 * @author Kirill Ozhigin on 16.12.2021
 */
class MovieLoadStateAdapter :
    LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: PagingHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.apply {
                headerProgressBar.isVisible = loadState is LoadState.Loading
                errorTextView.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = PagingHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateViewHolder(binding)
    }
}