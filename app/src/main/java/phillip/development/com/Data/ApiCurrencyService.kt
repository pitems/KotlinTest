package phillip.development.com.Data

import kotlinx.coroutines.Deferred
import phillip.development.com.Data.response.CurrencyResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Query URL https://api.exchangeratesapi.io/latest?base=EUR or USD

interface ApiCurrencyService {
    @GET("latest")
  fun getCurrency(
        @Query("base") type: String
    //Can add multiple Queries into this method
    ): Call<CurrencyResponse>



}