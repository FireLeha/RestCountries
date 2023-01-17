package com.example.restcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restcountries.data.CountryModel
import com.example.restcountries.retrofit.RetrofitInstance
import com.example.restcountries.retrofit.RetrofitServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesViewModel: ViewModel() {

    var liveDataList: MutableLiveData<List<CountryModel>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>> {
        return liveDataList
    }

    fun makeAPICall() {
        val retrofitInstance = RetrofitInstance.getRetroInstance()
        val retrofitService = retrofitInstance.create(RetrofitServiceInterface::class.java)
        val call = retrofitService.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>,
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })

    }

}