package sanjarbek.uz.currencycalculation.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sanjarbek.uz.currencycalculation.api.CurrencyApiService

object RetrofitClient {
    private const val BASE_URL = "https://cbu.uz/"

    val instance: CurrencyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApiService::class.java)
    }
}