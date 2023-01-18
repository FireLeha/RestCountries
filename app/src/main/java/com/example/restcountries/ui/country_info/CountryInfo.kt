package com.example.restcountries.ui.country_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.restcountries.data.CountryModel
import com.example.restcountries.databinding.FragmentCountryInfoBinding
import com.example.restcountries.ui.country_list.CountriesFragment
import com.example.restcountries.viewmodel.CountriesViewModel
import com.example.restcountries.viewmodel.CountryInfoViewModel

class CountryInfo:Fragment() {

    private var _binding: FragmentCountryInfoBinding? = null
    private val binding: FragmentCountryInfoBinding
    get() = _binding!!

    private val infoViewModel: CountryInfoViewModel by lazy {
        ViewModelProvider(this).get(CountryInfoViewModel::class.java)
    }

    lateinit var countryInfo:CountryModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        arguments?.let {
            it.getParcelable<CountryModel>("key")?.let {
                countryInfo= it
                with(binding){
                    tvName.text = it.name
                    tvRegion.text = it.region
                    tvCapital.text = it.capital
                    tvCurrency.text = it.currencies[0].toString()
                    tvTimezone.text = it.timezones[0]
                    flagImage.loadUrl(it.flag)
                }
            }

        }
    }

    private fun ImageView.loadUrl(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .components{add(SvgDecoder.Factory())}
            .build()

        val request = ImageRequest.Builder(this.context)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryInfoBinding.inflate(inflater, container,false)
        return binding.root
    }

    companion object {
        fun newInstance(bundle: Bundle):CountryInfo = CountryInfo().apply {
            arguments = bundle
        }
    }

}