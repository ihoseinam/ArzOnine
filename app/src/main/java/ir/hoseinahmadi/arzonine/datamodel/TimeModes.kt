package ir.hoseinahmadi.arzonine.datamodel

data class Date(
    val F: String,
    val j: String,
    val l: String,
    val Y: String,
)
data class DateModel(
    val date: Date,
    val help: String,
    val message: String,
    val success: Int
)