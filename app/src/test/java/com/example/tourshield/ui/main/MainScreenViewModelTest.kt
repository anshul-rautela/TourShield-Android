package com.example.tourshield.ui.main

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MainScreenViewModelTest {

    @Test
    fun uiState_initialState_isDefaultValues() = runTest {
        val viewModel = MainScreenViewModel()
        val state = viewModel.uiState.first()
        // Default: SOS on, countdown at 7
        assertTrue(state.manualSosEnabled)
        assertEquals(7, state.minutesAgo)
    }

    @Test
    fun onSosToggle_false_updatesState() = runTest {
        val viewModel = MainScreenViewModel()
        viewModel.onSosToggle(false)
        val state = viewModel.uiState.first()
        assertTrue(!state.manualSosEnabled)
    }
}
