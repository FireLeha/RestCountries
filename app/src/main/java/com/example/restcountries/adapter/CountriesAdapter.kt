package com.example.restcountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.restcountries.R
import com.example.restcountries.data.CountryModel
import com.example.restcountries.databinding.FragmentCountriesListBinding
import com.example.restcountries.utils.MyItemClickListener


class CountriesAdapter(val listener: MyItemClickListener) :
    RecyclerView.Adapter<CountriesAdapter.MainViewHolder>() {

    private var countriesData: List<CountryModel> = listOf()

    fun setCountriesList(countriesData: List<CountryModel>) {
        this.countriesData = countriesData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CountriesAdapter.MainViewHolder {
        return MainViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.fragment_countries_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountriesAdapter.MainViewHolder, position: Int) {
        holder.bind(this.countriesData[position])
    }

    override fun getItemCount(): Int {
        return countriesData.size
    }


    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentCountriesListBinding.bind(view)

        fun bind(data: CountryModel) {
            with(binding) {
                flagImage.loadUrl(data.flag)
                tvName.text = data.name
                tvName.setOnClickListener {
                    listener.onItemClick(data)
                }
            }
        }

        private fun ImageView.loadUrl(url: String) {
            val imageLoader = ImageLoader.Builder(this.context)
                .components { add(SvgDecoder.Factory()) }
                .build()

            val request = ImageRequest.Builder(this.context)
                .data(url)
                .target(this)
                .build()

            imageLoader.enqueue(request)
        }
    }
}