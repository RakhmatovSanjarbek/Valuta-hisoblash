package sanjarbek.uz.currencycalculation.domin.ui_model

import com.google.gson.annotations.SerializedName


data class CurrencyUIModel(
    @SerializedName("id") val id: Int,
    @SerializedName("Code") val code: String,
    @SerializedName("Ccy") val currency: String,
    @SerializedName("CcyNm_UZ") val currencyNameUz: String,
    @SerializedName("CcyNm_EN") val currencyNameEn: String,
    @SerializedName("Nominal") val nominal: String,
    @SerializedName("Rate") val rate: String,
    @SerializedName("Diff") val diff: String,
    @SerializedName("Date") val date: String,
)
