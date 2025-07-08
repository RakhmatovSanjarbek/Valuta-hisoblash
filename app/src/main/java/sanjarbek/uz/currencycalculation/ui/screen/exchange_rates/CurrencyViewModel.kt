package sanjarbek.uz.currencycalculation.ui.screen.exchange_rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sanjarbek.uz.currencycalculation.data.RetrofitClient
import sanjarbek.uz.currencycalculation.domin.ui_model.CurrencyUIModel

class CurrencyViewModel : ViewModel() {

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
}