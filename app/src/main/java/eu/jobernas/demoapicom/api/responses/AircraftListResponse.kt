package eu.jobernas.demoapicom.api.responses

import com.squareup.moshi.Json

data class AircraftListResponse(
    @Json val rows: List<AircraftFamily>)
