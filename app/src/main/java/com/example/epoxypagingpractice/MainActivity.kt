package com.example.epoxypagingpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
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
        val adapter = P3Adapter()
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        lifecycleScope.launch {
            viewModel.pageDataFlow.collectLatest {
             adapter
                 .submitData(it)
            }
        }

    }

    override fun onResume() {
        super.onResume()
    }
}