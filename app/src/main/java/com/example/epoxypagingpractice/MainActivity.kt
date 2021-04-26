package com.example.epoxypagingpractice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epoxypagingpractice.epoxy.EpoxyPagingController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var epoxyPagingController: EpoxyPagingController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        epoxyPagingInit(rv)
//        oldSchoolPagingInit(rv)
    }

    private fun epoxyPagingInit(rv:RecyclerView){
        epoxyPagingController = EpoxyPagingController()
        rv.adapter = epoxyPagingController.adapter
        lifecycleScope.launch {
            viewModel.pageDataFlow.collectLatest {
                Log.d("MainActivity", "$it")
                epoxyPagingController.submitData(it)
            }
        }
    }

    private fun oldSchoolPagingInit(rv:RecyclerView){
        val mainAdapter = P3Adapter()
        val loadAdapter = LoadStateAdapterImpl {
            mainAdapter.refresh()
        }

        rv.adapter = mainAdapter.withLoadStateFooter(loadAdapter)
        lifecycleScope.launch {
            viewModel.pageDataFlow.collectLatest {
                mainAdapter.submitData(it)
            }
        }
    }


    override fun onResume() {
        super.onResume()
    }
}