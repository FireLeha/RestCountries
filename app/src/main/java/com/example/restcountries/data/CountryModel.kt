package com.example.restcountries.data

data class CountryModel(
    val name: String?,
    val flag: String?,
    val region: String?,
    val capital: String?,
//    val currencies: List<Currency>,
    val timezones: List<String>?
)

//data class Currency(
//    val name: String,
//    val symbol: String
//)
