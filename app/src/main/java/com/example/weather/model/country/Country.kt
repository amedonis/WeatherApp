package com.example.weather.model.country


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("CapitalLatitude")
    val capitalLatitude: String,
    @SerializedName("CapitalLongitude")
    val capitalLongitude: String,
    @SerializedName("CapitalName")
    val capitalName: String,
    @SerializedName("ContinentName")
    val continentName: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("CountryName")
    val countryName: String
)