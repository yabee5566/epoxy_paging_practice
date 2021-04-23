package com.example.epoxypagingpractice

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

class TicketDataSource : PagingSource<Int, TicketData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TicketData> {
        val loadKey = params.key ?: 1
        try {
            var resp = FakeAPI().getTicket(loadKey)
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
}

class FakeAPI {
    private val NUM_PER_PAGE = 5

    suspend fun getTicket(index: Int): TicketResponse {
        val list = mutableListOf<TicketData>()
        if (index > NUM_PER_PAGE * 5) {
            for (i in index + 1..index + 3) {
                list.add(TicketData("<last_page> name $i", i))
            }
            delay(800)
            return TicketResponse(list, null)
        }

        for (i in index + 1..index + NUM_PER_PAGE) {
            list.add(TicketData("name $i", i))
        }
        delay(800)
        return TicketResponse(list, index + NUM_PER_PAGE + 1)
    }
}


data class TicketResponse(val ticketList: List<TicketData>, val nextPageIndex: Int?)