package com.example.epoxypagingpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.vh_ticket, parent, false)
) {

    fun bind(data:TicketData){
        itemView.findViewById<TextView>(R.id.id).setText(data.id.toString())
        itemView.findViewById<TextView>(R.id.name).setText(data.concertName.toString())
    }
}

