package com.example.restcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restcountries.data.CountryModel
import com.example.restcountries.utils.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesViewModel : ViewModel() {

    var liveDataList: MutableLiveData<List<CountryModel>> = MutableLiveData()

    private val repository: Repository by lazy {
        Repository()
    }

    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>> {
        return liveDataList
    }

    fun makeAPICall() {
        repository.getCountriesList(call)
    }

    private val call = object : Callback<List<CountryModel>> {
        override fun onResponse(
            call: Call<List<CountryModel>>,
            response: Response<List<CountryModel>>,
        ) {
            liveDataList.postValue(response.body())
        }

        override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
            liveDataList.postValue(null)
        }
    }

}