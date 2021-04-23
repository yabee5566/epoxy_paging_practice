package com.example.epoxypagingpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val rv = findViewById<RecyclerView>(R.id.rv)
        val mainAdapter = P3Adapter()
        val loadAdapter = LoadStateAdapterImpl{
mainAdapter.refresh()
        }
        rv.layoutManager = LinearLayoutManager(this)
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