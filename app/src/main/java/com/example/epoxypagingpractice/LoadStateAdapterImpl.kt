package com.example.epoxypagingpractice

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.epoxypagingpractice.databinding.VhLoadBinding

class LoadStateAdapterImpl(val retry:()->Unit) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VhLoadBinding.inflate(inflater)
        return LoadStateViewHolder(binding){
            retry.invoke()
            Log.d("aaaaaa","retryyyyyyyyyyy")
        }
    }
}