package phillip.development.com.Data.response

import com.google.gson.annotations.SerializedName


data class CurrencyResponse(
    val base: String,
    val date: String,
    @SerializedName("rates")
    val ratesData: RatesData
)