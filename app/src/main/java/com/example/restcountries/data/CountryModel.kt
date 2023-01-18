package com.example.restcountries.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryModel(
    val name: String,
    val flag: String,
    val region: String,
    val capital: String,
    val currencies: List<Currency>,
    val timezones: List<String>
) : Parcelable

@Parcelize
data class Currency(
    val name: String,
    val symbol: String
) : Parcelable
