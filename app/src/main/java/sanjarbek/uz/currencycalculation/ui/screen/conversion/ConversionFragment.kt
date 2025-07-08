package sanjarbek.uz.currencycalculation.ui.screen.conversion

import ItemSelectionDialogFragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import sanjarbek.uz.currencycalculation.R
import sanjarbek.uz.currencycalculation.databinding.FragmentConversionBinding
import sanjarbek.uz.currencycalculation.utils.Constants

class ConversionFragment : Fragment(R.layout.fragment_conversion) {
    private var _binding: FragmentConversionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConversionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConversionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val region = arguments?.getString(Constants.REGION) ?: ""
        var selectedCurrency = ""

        viewModel.currencyRates.observe(viewLifecycleOwner) { currencyList ->
            if (currencyList.isNotEmpty()) {
                val defaultCurrency = currencyList[0]
                binding.tvCcy.text = region
                binding.ivFlag.load("https://flagcdn.com/w160/${region.substring(0, 2).lowercase()}.png")
                binding.ivSelectFlag.load("https://flagcdn.com/w160/${defaultCurrency.currency.substring(0, 2).lowercase()}.png")
                binding.tvSelectCcy.text = defaultCurrency.currency

                binding.btnShowDialog.setOnClickListener {
                    val dialog = ItemSelectionDialogFragment(currencyList) { selectedItem ->
                        selectedCurrency = selectedItem
                        val result = viewModel.convertCurrency(1.0, region, selectedItem, currencyList)
                        binding.tvCanculos.text = "1 $region = ${String.format("%.4f", result)} $selectedItem"
                        val selectedCurrencyModel = currencyList.find { it.currency == selectedItem }
                        selectedCurrencyModel?.let {
                            binding.ivSelectFlag.load("https://flagcdn.com/w160/${it.currency.substring(0, 2).lowercase()}.png")
                        }
                        binding.tvSelectCcy.text = selectedItem
                    }
                    dialog.show(childFragmentManager, "ItemSelectionDialog")
                }

                binding.etAmount.addTextChangedListener { amountText ->
                    val amount = amountText.toString().toDoubleOrNull() ?: 0.0
                    val result = if (selectedCurrency.isEmpty()) {
                        viewModel.convertCurrency(amount, region, defaultCurrency.currency, currencyList)
                    } else {
                        viewModel.convertCurrency(amount, region, selectedCurrency, currencyList)
                    }
                    binding.tvResultAmount.text = String.format("%.2f", result)
                }

                if (selectedCurrency.isEmpty()) {
                    val result = viewModel.convertCurrency(1.0, region, defaultCurrency.currency, currencyList)
                    binding.tvCanculos.text = "1 $region = ${String.format("%.4f", result)} USD"
                }
            }
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
