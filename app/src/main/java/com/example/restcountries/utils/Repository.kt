package com.example.restcountries.utils

import com.example.restcountries.data.CountryModel

import retrofit2.Callback

class Repository : RepositoryCountryList {
    override fun getCountriesList(callback: Callback<List<CountryModel>>) {
        App.retrofit.getCountryList().enqueue(callback)
    }

}