package ir.hoseinahmadi.arzonine.Gold

import ir.hoseinahmadi.arzonine.datamodel.GoldModel
import retrofit2.Call
import retrofit2.http.GET

interface GoldApiService {
    @GET("currencies")
    fun getGolds(): Call<GoldModel>
}