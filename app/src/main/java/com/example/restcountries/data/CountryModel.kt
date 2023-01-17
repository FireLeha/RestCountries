package com.example.restcountries.data

data class CountryModel(
    val name: String?,
    val flag: String?,
    val region: String?,
    val capital: String?,
    val timezones: List<String>?
)
