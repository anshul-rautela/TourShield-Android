package com.example.tourshield.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val manualSosEnabled: Boolean = true,
    val liveSosEnabled: Boolean = false,

    /**
     * Minutes since last location share (Father & Mother).
     * Starts at 7, counts UP each minute, at 30 we show "Last shared recently",
     * then resets to 1 and repeats — infinite loop.
     */
    val minutesAgo: Int = 7,

    /**
     * Seconds elapsed since Live SOS was turned on.
     * 0 → "Live SOS started"
     * >0 → "Last shared X seconds ago" (increments every 15 s)
     */
    val liveSosSecondsElapsed: Int = 0,
)

class MainScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var liveSosJob: Job? = null

    init {
        startContactTimer()
    }

    // ── Father / Mother shared-location counter ──────────────────────────────
    // Counts up: 27 → 28 → 29 → 30 ("recently") → 1 → 2 → … → 30 → 1 → ∞
    private fun startContactTimer() {
        viewModelScope.launch {
            while (true) {
                delay(60_000L)
                val current = _uiState.value.minutesAgo
                // After showing 30 ("recently"), wrap back to 1
                val next = if (current >= 30) 1 else current + 1
                _uiState.value = _uiState.value.copy(minutesAgo = next)
            }
        }
    }

    // ── Manual SOS toggle ────────────────────────────────────────────────────
    fun onSosToggle(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(manualSosEnabled = enabled)
    }

    // ── Live Track SOS toggle ────────────────────────────────────────────────
    fun onLiveSosToggle(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(
            liveSosEnabled = enabled,
            liveSosSecondsElapsed = 0,  // reset counter every time it's toggled
        )
        if (enabled) {
            liveSosJob?.cancel()
            liveSosJob = viewModelScope.launch {
                while (true) {
                    delay(15_000L)
                    val current = _uiState.value.liveSosSecondsElapsed
                    _uiState.value = _uiState.value.copy(liveSosSecondsElapsed = current + 15)
                }
            }
        } else {
            liveSosJob?.cancel()
            liveSosJob = null
        }
    }
}
