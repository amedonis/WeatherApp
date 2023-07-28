package com.example.weather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.model.weather.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: MutableLiveData<WeatherResponse?> = _weatherData

    private val apiKey = "bea0cba474730aba480711b6636f0521"

    fun fetchWeatherData(lat: String, lon: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherResponse = weatherRepository.getWeatherData(lat, lon, apiKey)
            _weatherData.postValue(weatherResponse)
        }

    }
}