package com.example.restcountries.utils

import com.example.restcountries.data.CountryModel

interface MyItemClickListener {
    fun onItemClick(data: CountryModel)
}