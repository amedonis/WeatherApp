package com.example.weather.data.remote

import com.example.weather.model.country.Country
import retrofit2.http.GET

interface CountryService {
    @GET("demos/country-capitals.json")
    suspend fun getCountries(): List<Country>
}