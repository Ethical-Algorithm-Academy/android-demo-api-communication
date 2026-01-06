package eu.jobernas.demoapicom

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.jobernas.demoapicom.api.FlightRadarRepository
import eu.jobernas.demoapicom.api.responses.AirportsResponse
import eu.jobernas.demoapicom.api.responses.CommonResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: FlightRadarRepository,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
                    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main) :
    ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.simpleName.orEmpty()
    }

    val onResult: SharedFlow<String>
        get() = _onResult.asSharedFlow()
    private val _onResult: MutableSharedFlow<String> = MutableSharedFlow()

    val onError: SharedFlow<Throwable>
        get() = _onError.asSharedFlow()
    private val _onError: MutableSharedFlow<Throwable> = MutableSharedFlow(extraBufferCapacity = 1)

    fun getFlightsByAirline(airline: String) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val result = repository.flightRadarRequestsInterface.getFlightsByAirline(airline)
                val description =
                    result.flights.joinToString(separator = "\n") { it.flightNumber.toString() }
                _onResult.emit(description)

            } catch (error: Throwable) {
                Log.e(TAG, "Error: ${error.message}", error)
                _onError.emit(error)

            }
        }
    }

    fun getAirCraftsList() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val result = repository.flightRadarRequestsInterface.getAirCraftsList()
                val description = result.rows.joinToString(separator = "\n") { it.description }
                _onResult.emit(description)
            } catch (error: Throwable) {
                Log.e(TAG, "Error: ${error.message}", error)
                _onError.emit(error)

            }
        }
    }

    fun getAirportList() {
        repository.getAirportList(object : CommonResponse.OnResponse<AirportsResponse> {
            override fun onSuccess(response: AirportsResponse?) {
                Log.d(TAG, "Response: getAirportList: ${response?.version}")
            }

            override fun onError(t: Throwable) {
                Log.e(TAG, "Error", t)
            }
        })
    }

}