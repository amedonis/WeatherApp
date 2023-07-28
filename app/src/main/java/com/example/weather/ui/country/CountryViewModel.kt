package com.example.weather.ui.country

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.repository.CountryRepository
import com.example.weather.model.country.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val repository: CountryRepository): ViewModel() {
    val countryInfo = MutableLiveData<List<Country>>()

    init {
        fetchCountryInfo()
    }

    private fun fetchCountryInfo() = viewModelScope.launch {
        countryInfo.value = repository.getCountries()
    }
}