package eu.jobernas.demoapicom

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import eu.jobernas.demoapicom.core.ViewModelFactory
import eu.jobernas.demoapicom.core.collectOnLifecycleScope
import eu.jobernas.demoapicom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        ViewModelFactory()
    })
    private var binding: ActivityMainBinding? = null


//    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.binding = binding
        viewModel.apply {
            onError.collectOnLifecycleScope(this@MainActivity) { error ->
                binding.mainConsoleTextView.text = error.message.orEmpty()
            }
            onResult.collectOnLifecycleScope(this@MainActivity) { result ->
                binding.mainConsoleTextView.text = result
            }
            getFlightsByAirline("AXM")
        }

//        getAirportList()
    }

}