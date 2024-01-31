package ir.hoseinahmadi.arzonine.remote

import ir.hoseinahmadi.arzonine.datamodel.DateModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApiService {
    @GET("date/now")
    fun getTime(
        @Query("short") short: Boolean,
    ): Call<DateModel>
}