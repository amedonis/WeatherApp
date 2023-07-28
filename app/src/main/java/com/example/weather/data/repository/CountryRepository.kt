package com.example.weather.data.repository

import com.example.weather.data.remote.CountryService
import javax.inject.Inject

class CountryRepository @Inject constructor(private val countryService: CountryService){
        suspend fun getCountries() = countryService.getCountries()
}