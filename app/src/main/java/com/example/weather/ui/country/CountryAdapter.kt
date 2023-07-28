package com.example.weather.ui.country

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.country.Country

class CountryAdapter (
    private var countries: List<Country>, private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName: TextView = view.findViewById(R.id.text_view_country)
        val countryCapital: TextView = view.findViewById(R.id.text_view_capital)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        val country = countries[position]
        holder.countryName.text = country.countryName
        holder.countryCapital.text = country.capitalName
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(country)
        }

    }

    fun updateCountries(newCountries: List<Country>) {
        this.countries = newCountries
        notifyDataSetChanged()
    }

    override fun getItemCount() = countries.size

    interface OnItemClickListener {
        fun onItemClick(country: Country)
    }

}

