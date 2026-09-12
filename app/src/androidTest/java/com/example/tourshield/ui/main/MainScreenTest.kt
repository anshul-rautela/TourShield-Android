package com.example.tourshield.ui.main

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.tourshield.theme.TourShieldTheme
import org.junit.Rule
import org.junit.Test

/** UI tests for [HomeScreen]. */
class MainScreenTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun header_isDisplayed() {
        composeTestRule.setContent {
            TourShieldTheme {
                HomeScreen(
                    sosEnabled = true,
                    minutesAgo = 7,
                    onSosToggle = {},
                )
            }
        }
        composeTestRule.onNodeWithText("Hello User").assertIsDisplayed()
    }

    @Test
    fun sosLabel_isDisplayed() {
        composeTestRule.setContent {
            TourShieldTheme {
                HomeScreen(
                    sosEnabled = true,
                    minutesAgo = 5,
                    onSosToggle = {},
                )
            }
        }
        composeTestRule.onNodeWithText("Manual SOS").assertIsDisplayed()
    }

    @Test
    fun contactNames_areDisplayed() {
        composeTestRule.setContent {
            TourShieldTheme {
                HomeScreen(
                    sosEnabled = true,
                    minutesAgo = 3,
                    onSosToggle = {},
                )
            }
        }
        composeTestRule.onNodeWithText("Father").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mother").assertIsDisplayed()
    }

    @Test
    fun contactsHeader_showsSubtitle() {
        composeTestRule.setContent {
            TourShieldTheme {
                HomeScreen(
                    sosEnabled = true,
                    minutesAgo = 7,
                    onSosToggle = {},
                )
            }
        }
        composeTestRule.onNodeWithText("Under safety policy, your live location", substring = true).assertIsDisplayed()
    }
}
