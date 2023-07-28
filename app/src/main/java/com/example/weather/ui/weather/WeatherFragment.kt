package com.example.weather.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weather.R
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.example.weather.model.weather.Weather
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class WeatherFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCurrentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_current_weather, container, false
        )

        val lat = requireArguments().getString("latitude")
        val lon = requireArguments().getString("longitude")
        val capital = requireArguments().getString("capital")


        // Observe the weather data and update UI when it changes
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { weatherResponse ->
            weatherResponse?.let {
                binding.tvName.text = it.name
                binding.tvCapital.text = capital
                binding.tvTemp.text = String.format("%.1f", (it.main.temp - 273.15))
                binding.tvSpeed.text = String.format("%.1f", (it.wind.speed * 1.6))
                binding.tvMin.text = String.format("%.1f", (it.main.tempMin - 273.15))
                binding.tvMax.text = String.format("%.1f", (it.main.tempMax - 273.15))
                binding.tvSunriseTime.text = convertUnixTimestampToTime(it.sys.sunrise.toLong())
                binding.tvSunsetTime.text = convertUnixTimestampToTime(it.sys.sunset.toLong())

                var weatherList: List<Weather> = it.weather
                binding.tvMain.text = weatherList.first().main


            }
        })

        // Fetch weather data with latitude and longitude
        if (lat != null && lon != null) {
            viewModel.fetchWeatherData(lat, lon)
        }


        return binding.root
    }


    fun convertUnixTimestampToTime(unixTimestamp: Long): String {
        val date = Date(unixTimestamp * 1000L)
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault()) // Use "HH:mm" for 24-hour format
        return sdf.format(date)
    }

}