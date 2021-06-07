package com.example.epoxypagingpractice.epoxy

import android.util.Log
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.epoxypagingpractice.EmptyHolderModel_
import com.example.epoxypagingpractice.TicketData
import com.example.epoxypagingpractice.emptyHolder
import com.example.epoxypagingpractice.titleWithMoreHolder

class EpoxyPagingController : PagingDataEpoxyController<TicketData>() {
    private var state: CombinedLoadStates? = null

    init {
        addLoadStateListener {
            state = it
            Log.d("EpoxyPagingController", "$state")
            requestModelBuild()
        }
    }


    override fun addModels(models: List<EpoxyModel<*>>) {
        titleWithMoreHolder {
            id("title")
            titleText("Title")
        }
        if (models.isEmpty()){
            emptyHolder {
                id("empty")
            }
        }

        super.addModels(models)
        if (state?.append is LoadState.Loading) {
            loadStateModelView {
                loadState(LoadState.Loading)
                id("loading")
            }

        } else if (state?.prepend is LoadState.Error || state?.append is LoadState.Error || state?.refresh is LoadState.Error) {
            Log.d("EpoxyPagingController", "error clause")
            loadStateModelView {
                id("error")
                loadState(LoadState.Error(Throwable("something went wrong")))
                    .retryListener { retry() }
            }
        }
    }


    override fun buildItemModel(currentPosition: Int, item: TicketData?): EpoxyModel<*> {
        Log.d("EpoxyPagingController", "buildItemModel $currentPosition")
        return TicketModelViewModel_()
            .id(item!!.id)
            .ticketId(item.id).name(item.concertName)
    }
}