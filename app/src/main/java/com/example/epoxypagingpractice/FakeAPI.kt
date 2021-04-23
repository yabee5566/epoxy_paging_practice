package com.example.epoxypagingpractice

import kotlinx.coroutines.delay

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