package com.example.restcountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restcountries.adapter.CountryListAdapter
import com.example.restcountries.databinding.ActivityMainBinding
import com.example.restcountries.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: CountryListAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        with(binding){
            countryListRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerAdapter = CountryListAdapter(this@MainActivity)
            countryListRecyclerview.adapter = recyclerAdapter
        }
    }

    private fun initViewModel() {
        val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if (it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error getting list)", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }

}