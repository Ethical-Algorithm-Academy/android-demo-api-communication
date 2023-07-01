package eu.jobernas.demoapicom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import eu.jobernas.demoapicom.api.FlightRadarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

//    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flightRadarRepository = FlightRadarRepository()
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = flightRadarRepository.flightRadarRequestsInterface.getAirCraftsList()
                result.rows.forEach {
                    Log.d(TAG, "Description: ${it.description}")
                }
            }catch (error: Throwable) {
                Log.e(TAG, "Error: ${error.message}", error)
            }
        }
    }

}