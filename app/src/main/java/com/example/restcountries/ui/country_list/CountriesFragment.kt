package com.example.restcountries.ui.country_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restcountries.adapter.CountriesAdapter
import com.example.restcountries.databinding.FragmentCountriesBinding
import com.example.restcountries.viewmodel.CountriesViewModel

class CountriesFragment : Fragment() {

    private val adapter: CountriesAdapter by lazy { CountriesAdapter() }

    private val viewModel: CountriesViewModel by lazy {
        ViewModelProvider(this).get(CountriesViewModel::class.java) }

    private var _binding: FragmentCountriesBinding? = null
    private val binding: FragmentCountriesBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        with(binding){
            fragmentCountriesRecyclerview.adapter = adapter

        }
    }

    private fun initViewModel() {
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setCountriesList(it)
            } else {
                Toast.makeText(context, "Error getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = CountriesFragment()
    }

}