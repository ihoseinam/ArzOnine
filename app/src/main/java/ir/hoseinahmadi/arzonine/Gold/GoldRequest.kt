package ir.hoseinahmadi.arzonine.Gold

import ir.hoseinahmadi.arzonine.datamodel.DateModel
import ir.hoseinahmadi.arzonine.datamodel.GoldModel


interface GoldRequest {
    fun onSuccess(data: GoldModel)

    fun onNotSuccess(message:String)

    fun onError(error: String)
}