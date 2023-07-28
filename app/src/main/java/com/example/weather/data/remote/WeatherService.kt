package com.example.weather.data.remote

import com.example.weather.model.weather.WeatherResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherService {
    @GET("weather")
    suspend fun getWeatherInfo(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}
