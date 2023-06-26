package eu.jobernas.demoapicom.api.responses

import com.squareup.moshi.Json

data class AircraftFamily(
    @Json val description: String,
    @Json val models: List<Aircraft>)
