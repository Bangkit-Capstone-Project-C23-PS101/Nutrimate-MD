package com.example.nutrimate.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrimate.R
import com.example.nutrimate.data.Food
import com.example.nutrimate.data.FoodData
import com.example.nutrimate.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        val foodlist = FoodData.listfood
        val adapter = FoodAdapter(foodlist)
        val recyclerView: RecyclerView = binding.rvSearchFood
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = adapter
        binding.etSearchFood.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newlist = ArrayList<Food>()
                for(food in foodlist){
                    if(food.name.lowercase().contains(s.toString().lowercase())){
                        newlist.add(food)
                    }
                }
                val newadapter = FoodAdapter(newlist)
                recyclerView.adapter = newadapter
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        return binding.root
    }

    companion object {

    }
}