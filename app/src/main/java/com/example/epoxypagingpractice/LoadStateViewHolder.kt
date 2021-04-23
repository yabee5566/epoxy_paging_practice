package com.example.epoxypagingpractice

import android.widget.Button
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.epoxypagingpractice.databinding.VhLoadBinding

class LoadStateViewHolder(
    private val binding: VhLoadBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val retry: Button = binding.retryBtn
        .also {
            it.setOnClickListener { retry() }
        }

    fun bind(loadState: LoadState) {
        with(binding) {
            if (loadState is LoadState.Error) {
                loadingText.text = loadState.error.localizedMessage
            } else if (loadState is LoadState.Loading) {
                loadingText.text = "loading"
            }

            retryBtn.isVisible = loadState is LoadState.Error
            loadingText.isVisible = loadState is LoadState.Loading
        }
    }
}
