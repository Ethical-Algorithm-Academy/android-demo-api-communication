package eu.jobernas.demoapicom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import eu.jobernas.demoapicom.api.FlightRadarRepository
import eu.jobernas.demoapicom.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

//    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val flightRadarRepository = FlightRadarRepository()
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = flightRadarRepository.flightRadarRequestsInterface.getAirCraftsList()
                val description = result.rows.joinToString(separator = "\n") { it.description }
                withContext(Dispatchers.Main) {
                    binding.mainConsoleTextView.text = description
                }
            }catch (error: Throwable) {
                Log.e(TAG, "Error: ${error.message}", error)
                withContext(Dispatchers.Main) {
                    binding.mainConsoleTextView.text = error.message
                }
            }
        }
    }

}