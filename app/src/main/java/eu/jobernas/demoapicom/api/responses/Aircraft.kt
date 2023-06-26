package eu.jobernas.demoapicom.api.responses

import com.squareup.moshi.Json

data class Aircraft(
    @Json(name = "Name") val name: String,
    @Json(name = "Code") val code: String)
