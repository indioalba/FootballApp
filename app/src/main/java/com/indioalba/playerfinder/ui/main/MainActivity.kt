package com.indioalba.playerfinder.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.indioalba.playerfinder.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: FootballAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FootballAdapter(viewModel::onItemClicked)
        recycler.adapter = adapter

        viewModel.item.observe(this, Observer {
            adapter.items = it
        })

        searchButton.setOnClickListener {
            viewModel.search(searchQuery.text.toString())
        }
    }
}
