package com.example.restcountries.utils

import android.app.Application
import com.example.restcountries.retrofit.RetrofitServiceInterface
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        const val BUNDLE_KEY = "key"
        const val BASE_URL = "https://restcountries.com/v2/"
        const val ENDPOINT = "all"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build().create(RetrofitServiceInterface::class.java)
    }
}