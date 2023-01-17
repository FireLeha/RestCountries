package com.example.restcountries.retrofit

import com.example.restcountries.data.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServiceInterface {

    @GET("all")
    fun getCountryList(): Call<List<CountryModel>>

}