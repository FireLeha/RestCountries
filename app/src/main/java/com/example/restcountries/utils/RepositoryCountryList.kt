package com.example.restcountries.utils

import com.example.restcountries.data.CountryModel
import retrofit2.Callback

interface RepositoryCountryList {
    fun getCountriesList(callback: Callback<List<CountryModel>>)
}