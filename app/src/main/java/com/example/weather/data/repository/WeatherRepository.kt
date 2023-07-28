package com.example.weather.data.repository

import com.example.weather.data.remote.WeatherService
import com.example.weather.model.weather.WeatherResponse
import retrofit2.Response
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {

    suspend fun getWeatherData(lat: String, lon: String, apiKey: String): WeatherResponse? {
        return try {
            val response: Response<WeatherResponse> = weatherService.getWeatherInfo(lat, lon, apiKey)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}