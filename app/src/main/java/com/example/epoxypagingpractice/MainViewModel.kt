package com.example.epoxypagingpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MainViewModel : ViewModel() {

    val NUM_PER_PAGE = 5

    val pageDataFlow = Pager(PagingConfig(pageSize = NUM_PER_PAGE)){
        TicketDataSource()
    }.flow.cachedIn(viewModelScope)

}