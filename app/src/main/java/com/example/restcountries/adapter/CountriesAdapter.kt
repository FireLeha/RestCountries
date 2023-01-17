package com.example.restcountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountries.R
import com.example.restcountries.data.CountryModel

class CountriesAdapter: RecyclerView.Adapter<CountriesAdapter.MainViewHolder>() {

    private var countriesData: List<CountryModel> = listOf()

    fun setCountriesList(countriesData: List<CountryModel>) {
        this.countriesData = countriesData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapter.MainViewHolder {
        return MainViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_countries_list, parent, false))
    }

    override fun onBindViewHolder(holder: CountriesAdapter.MainViewHolder, position: Int) {
        holder.bind(this.countriesData[position])
    }

    override fun getItemCount(): Int {
        return countriesData.size
    }



    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(data: CountryModel){
            with(itemView){
                findViewById<TextView>(R.id.tvName).text =data.name
            }
        }
    }
}