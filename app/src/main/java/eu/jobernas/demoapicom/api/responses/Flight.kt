package eu.jobernas.demoapicom.api.responses

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson

data class Flight(val latitude: Double?,
                  val longitude: Double?,
                  val altitude: Int?,
                  val speed: Int?,
                  val heading: Int?,
                  val flightNumber: String?,
                  val aircraftModel: String?,
                  val registrationNumber: String?,
                  val departureAirportCode: String?,
                  val arrivalAirportCode: String?) {

    class Adapter {

        @ToJson
        fun toJson(flight: Flight): String {
            throw UnsupportedOperationException("toJson method is not implemented.")
        }

        @FromJson
        fun fromJson(reader: JsonReader): Flight? {
//            Example for an object with a key with several types
//            val stream2: Map<String, *> = (reader.readJsonValue() as? Map<String, *>) ?: return null
//            val isLoaded = when(val tmpIsLoaded =  stream2["isLoaded"]) {
//                is String -> tmpIsLoaded.toBoolean()
//                is Int -> tmpIsLoaded == 1
//                is Boolean -> tmpIsLoaded
//                else -> false
//            }

            val stream: List<*> = (reader.readJsonValue() as? List<*>) ?: return null
            val latitude = stream.getOrNull(2)?.toString()?.toDoubleOrNull()
            val longitude = stream.getOrNull(3)?.toString()?.toDoubleOrNull()
            val altitude = stream.getOrNull(4)?.toString()?.toIntOrNull()
            val speed = stream.getOrNull(5)?.toString()?.toIntOrNull()
            val heading = stream.getOrNull(6)?.toString()?.toIntOrNull()
            val flightNumber = stream.getOrNull(8)?.toString()
            val aircraftModel = stream.getOrNull(9)?.toString()
            val registrationNumber = stream.getOrNull(10)?.toString()
            val departureAirportCode = stream.getOrNull(12)?.toString()
            val arrivalAirportCode = stream.getOrNull(13)?.toString()
            return Flight(latitude, longitude, altitude, speed, heading, flightNumber, aircraftModel, registrationNumber, departureAirportCode, arrivalAirportCode)
        }
    }
}
