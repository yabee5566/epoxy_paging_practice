package com.example.epoxypagingpractice

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import java.io.IOException

class TicketDataSource : PagingSource<Int, TicketData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TicketData> {
        val loadKey = params.key ?: 1
        try {
            var resp = FakeAPI().getTicket(loadKey)

            if (loadKey >10 && !isErrorOnce){
                isErrorOnce = true
                throw IOException("waaaaaaa!")
            }

            return LoadResult.Page(
                data = resp.ticketList,
                prevKey = null,
                nextKey = resp.nextPageIndex
            )
        }catch (exception:Exception ) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TicketData>): Int? {
        //PlanA
        return null // let fun load(..) determine the refresh key
        //Plan B
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
    }

    companion object{
        var isErrorOnce = false
    }
}




