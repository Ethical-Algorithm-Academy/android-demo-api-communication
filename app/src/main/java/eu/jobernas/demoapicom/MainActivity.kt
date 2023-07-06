package eu.jobernas.demoapicom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import eu.jobernas.demoapicom.api.FlightRadarRepository
import eu.jobernas.demoapicom.api.responses.AirportsResponse
import eu.jobernas.demoapicom.api.responses.CommonResponse
import eu.jobernas.demoapicom.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private val flightRadarRepository by lazy {
        FlightRadarRepository()
    }
    private var binding: ActivityMainBinding? = null


//    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.binding = binding
        getFlightsByAirline("AXM")
//        getAirportList()
    }

    private fun getFlightsByAirline(airline: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = flightRadarRepository.flightRadarRequestsInterface.getFlightsByAirline(airline)
                val description = result.flights.joinToString(separator = "\n") { it.flightNumber.toString() }
                withContext(Dispatchers.Main) {
                    binding?.mainConsoleTextView?.text = description
                }
            }catch (error: Throwable) {
                Log.e(TAG, "Error: ${error.message}", error)
                withContext(Dispatchers.Main) {
                    binding?.mainConsoleTextView?.text = error.message
                }
            }
        }
    }

    private fun getAirCraftsList() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = flightRadarRepository.flightRadarRequestsInterface.getAirCraftsList()
                val description = result.rows.joinToString(separator = "\n") { it.description }
                withContext(Dispatchers.Main) {
                    binding?.mainConsoleTextView?.text = description
                }
            }catch (error: Throwable) {
                Log.e(TAG, "Error: ${error.message}", error)
                withContext(Dispatchers.Main) {
                    binding?.mainConsoleTextView?.text = error.message
                }
            }
        }
    }

    private fun getAirportList() {
        flightRadarRepository.getAirportList( object: CommonResponse.OnResponse<AirportsResponse> {
            override fun onSuccess(response: AirportsResponse?) {
                Log.d(TAG, "Response: getAirportList: ${response?.version}")
            }

            override fun onError(t: Throwable) {
                Log.e(TAG, "Error", t)
            }
        })
    }

}