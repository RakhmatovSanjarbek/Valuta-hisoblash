package sanjarbek.uz.currencycalculation.ui.screen.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sanjarbek.uz.currencycalculation.data.RetrofitClient
import sanjarbek.uz.currencycalculation.domin.ui_model.CurrencyUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversionViewModel : ViewModel() {

    private val _currencyRates = MutableLiveData<List<CurrencyUIModel>>()
    val currencyRates: LiveData<List<CurrencyUIModel>> get() = _currencyRates

    init {
        fetchCurrencyRates()
    }

    private fun fetchCurrencyRates() {
        RetrofitClient.instance.getCurrencyRates().enqueue(object : Callback<List<CurrencyUIModel>> {
            override fun onResponse(call: Call<List<CurrencyUIModel>>, response: Response<List<CurrencyUIModel>>) {
                if (response.isSuccessful) {
                    _currencyRates.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<CurrencyUIModel>>, t: Throwable) {
            }
        })
    }

    fun convertCurrency(
        amount: Double,
        fromCurrency: String,
        toCurrency: String,
        currencies: List<CurrencyUIModel>
    ): Double {
        val fromRate = currencies.find { it.currency == fromCurrency }?.rate?.toFloat() ?: return 0.0
        val toRate = currencies.find { it.currency == toCurrency }?.rate?.toFloat() ?: return 0.0
        return amount * (fromRate / toRate)
    }
}
