package eu.jobernas.demoapicom.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Collect repeat on start
 *
 * @param T
 * @param owner
 * @param block
 * @receiver
 */
fun <T> Flow<T>.collectRepeatOn(owner: LifecycleOwner,
                                state: Lifecycle.State = Lifecycle.State.STARTED,
                                block: (T) -> Unit) = owner.lifecycleScope.launch {
    owner.lifecycle.repeatOnLifecycle(state = state) {
        collectLatest(block)
    }
}

/**
 * Collect
 *
 * @param T
 * @param owner
 * @param block
 * @receiver
 */
fun <T> Flow<T>.collectOnLifecycleScope(owner: LifecycleOwner, block: (T) -> Unit) =
    owner.lifecycleScope.launch {
        collect(block)
    }
