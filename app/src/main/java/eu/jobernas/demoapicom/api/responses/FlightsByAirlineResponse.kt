package eu.jobernas.demoapicom.api.responses

import com.squareup.moshi.Json

data class FlightsByAirlineResponse(
    @Json(name = "aircraft") val flights: List<Flight>)