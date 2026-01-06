package eu.jobernas.demoapicom.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.jobernas.demoapicom.MainViewModel
import eu.jobernas.demoapicom.api.FlightRadarRepository

/**
 * View model factory
 *
 * @constructor Create empty View model factory
 */
class ViewModelFactory : ViewModelProvider.Factory {

    private val flightRadarRepository by lazy {
        FlightRadarRepository()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) -> MainViewModel(flightRadarRepository)
            else -> throw IllegalArgumentException("View Model not recognized")
        }
    } as T
}