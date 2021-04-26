package com.example.epoxypagingpractice.epoxy

import android.util.Log
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.epoxypagingpractice.TicketData

class EpoxyPagingController : PagingDataEpoxyController<TicketData>() {

    override fun buildItemModel(currentPosition: Int, item: TicketData?): EpoxyModel<*> {
        Log.d("EpoxyPagingController", "buildItemModel $currentPosition")
        return TicketModelViewModel_()
            .id(item!!.id)
            .ticketId(item.id).name(item.concertName)
    }
}