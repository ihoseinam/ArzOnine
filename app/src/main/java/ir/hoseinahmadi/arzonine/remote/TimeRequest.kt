package ir.hoseinahmadi.arzonine.remote

import ir.hoseinahmadi.arzonine.datamodel.DateModel


interface TimeRequest {
    fun onSuccess(data: DateModel)

    fun onNotSuccess(message:String)

    fun onError(error: String)
}