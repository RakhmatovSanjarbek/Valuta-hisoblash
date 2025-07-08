package sanjarbek.uz.currencycalculation.ui.screen.exchange_rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import sanjarbek.uz.currencycalculation.R
import sanjarbek.uz.currencycalculation.databinding.FragmentExchangeRatesBinding
import sanjarbek.uz.currencycalculation.utils.Constants
import java.text.SimpleDateFormat
import java.util.Locale

class ExchangeRatesFragment : Fragment(R.layout.fragment_exchange_rates) {
    private var _binding: FragmentExchangeRatesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyViewModel by viewModels()

    private val currencyAdapter: CurrenciesAdapter by lazy {
        CurrenciesAdapter { currency ->
            onCurrencyItemClicked(currency)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentExchangeRatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vtToday.text = getCurrentDateFormatted()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = currencyAdapter

        viewModel.currencyRates.observe(viewLifecycleOwner) { currencyList ->
            currencyAdapter.submitList(currencyList.toList())
            println(":::List:${currencyList.toList()}")
        }
    }

    private fun onCurrencyItemClicked(currency: String) {
        val bundle = bundleOf(Constants.REGION to currency)
        findNavController().navigate(
            R.id.action_exchangeRatesFragment_to_conversionFragment,
            bundle
        )
    }

    private fun getCurrentDateFormatted(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.format(System.currentTimeMillis())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
