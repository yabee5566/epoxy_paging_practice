package com.example.epoxypagingpractice

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class P3Adapter: PagingDataAdapter<TicketData, TicketViewHolder>(diffItemCallback) {

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(parent)
    }
     object diffItemCallback : DiffUtil.ItemCallback<TicketData>(){
         override fun areItemsTheSame(oldItem: TicketData, newItem: TicketData): Boolean {
             return oldItem == newItem
         }

         override fun areContentsTheSame(oldItem: TicketData, newItem: TicketData): Boolean {
             return oldItem.id == newItem.id
         }
     }
}