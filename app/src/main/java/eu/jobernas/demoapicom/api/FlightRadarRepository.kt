package eu.jobernas.demoapicom.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.jobernas.demoapicom.api.responses.AircraftListResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.util.concurrent.TimeUnit

class FlightRadarRepository() {
    /**
     * Static Methods
     */
    companion object {
        // Constants
        private val TAG = FlightRadarRepository::class.java.simpleName
    }

    object Endpoints {
        const val AIR_CRAFTS_LIST = "aircrafts/list"
    }

    // Clients Configuration
    private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .connectTimeout(ApiConfig.Connection.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(ApiConfig.Connection.READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(ApiConfig.Connection.WRITE_TIMEOUT, TimeUnit.SECONDS)

    private val moshiBuilder: Moshi.Builder = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())

    private val defaultMoshiConverterFactory: MoshiConverterFactory = MoshiConverterFactory
        .create(moshiBuilder.build())
        .withNullSerialization()
        .asLenient()

    private val okHttpClient: OkHttpClient by lazy {
        okHttpClientBuilder.build()
    }

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(ApiConfig.url)
        .client(okHttpClient)
        .addConverterFactory(defaultMoshiConverterFactory)

    private val retrofitClient: Retrofit by lazy {
        retrofitBuilder.build()
    }

    // Request Configuration
    interface FlightRadarRequestsInterface {

        @GET(Endpoints.AIR_CRAFTS_LIST)
        suspend fun getAirCraftsList(@Header(ApiConfig.Headers.API_KEY) apiKey: String = ApiConfig.Headers.Values.API_KEY,
                             @Header(ApiConfig.Headers.API_HOST) apiHost: String = ApiConfig.Headers.Values.API_HOST): AircraftListResponse

    }

    val flightRadarRequestsInterface: FlightRadarRequestsInterface = retrofitClient
            .create(FlightRadarRequestsInterface::class.java)
}