package sanjarbek.uz.currencycalculation.api

import retrofit2.Call
import retrofit2.http.GET
import sanjarbek.uz.currencycalculation.domin.ui_model.CurrencyUIModel

interface CurrencyApiService {
    @GET("uz/arkhiv-kursov-valyut/json/")
    fun getCurrencyRates(): Call<List<CurrencyUIModel>>
}