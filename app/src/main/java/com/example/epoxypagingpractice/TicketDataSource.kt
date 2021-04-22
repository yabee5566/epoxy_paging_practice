package com.example.epoxypagingpractice

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource

class TicketDataSource :ItemKeyedDataSource<Int, TicketData>(){
    override fun getKey(item: TicketData) = item.id

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<TicketData>
    ) {
        val items = fetchItems(params.requestedInitialKey,
            params.requestedLoadSize)
        callback.onResult(items)
    }

    override fun loadAfter(
        params: LoadParams<Date>,
        callback: LoadCallback<TicketData>) {
        val items = fetchItemsAfter(
            date = params.key,
            limit = params.requestedLoadSize)
        callback.onResult(items)
    }
}
