package com.example.restcountries.ui.country_list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.restcountries.R
import com.example.restcountries.adapter.CountriesAdapter
import com.example.restcountries.data.CountryModel
import com.example.restcountries.databinding.FragmentCountriesBinding
import com.example.restcountries.ui.country_info.CountryInfo
import com.example.restcountries.utils.App.Companion.BUNDLE_KEY
import com.example.restcountries.utils.MyItemClickListener
import com.example.restcountries.viewmodel.CountriesViewModel

class CountriesFragment : Fragment(), MyItemClickListener {

    private val adapter: CountriesAdapter by lazy { CountriesAdapter(this) }

    private val viewModel: CountriesViewModel by lazy {
        ViewModelProvider(this).get(CountriesViewModel::class.java)
    }

    private var _binding: FragmentCountriesBinding? = null
    private val binding: FragmentCountriesBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        with(binding) {
            fragmentCountriesRecyclerview.adapter = adapter

        }
    }

    private fun initViewModel() {
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setCountriesList(it)
            } else {
                Toast.makeText(context, "Error getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = CountriesFragment()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_button -> {
                viewModel.makeAPICall()
                Toast.makeText(context, "Данные обновились", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onItemClick(data: CountryModel) {
        Bundle().apply {
            putParcelable(BUNDLE_KEY, data)
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activityContainer, CountryInfo.newInstance(bundle = this@apply))
                    .addToBackStack("").commit()
            }
        }
    }

}