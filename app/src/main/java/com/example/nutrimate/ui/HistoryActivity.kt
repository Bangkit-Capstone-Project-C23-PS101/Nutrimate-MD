package com.example.nutrimate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrimate.R
import com.example.nutrimate.data.FoodDatabase
import com.example.nutrimate.databinding.ActivityHistoryBinding


class HistoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryBinding
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        historyViewModel = obtainViewModel(this)
        val recyclerView: RecyclerView = binding.rvHistory
        recyclerView.layoutManager = LinearLayoutManager(this)
        historyViewModel.getAllFoods().observe(this){
            val adapter = HistoryAdapter(it as ArrayList<FoodDatabase>)
            recyclerView.adapter = adapter
        }
        getSupportActionBar()?.hide()
        setContentView(binding.root)
    }

    private fun obtainViewModel(activity: AppCompatActivity): HistoryViewModel {
        val factory = FoodViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(HistoryViewModel::class.java)
    }
}