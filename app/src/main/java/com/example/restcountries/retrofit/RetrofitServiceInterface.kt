package com.example.restcountries.retrofit

import com.example.restcountries.data.CountryModel
import com.example.restcountries.utils.App.Companion.ENDPOINT
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServiceInterface {

    @GET(ENDPOINT)
    fun getCountryList(): Call<List<CountryModel>>

}