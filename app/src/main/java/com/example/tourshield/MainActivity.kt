package com.example.tourshield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.tourshield.theme.DarkBackground
import com.example.tourshield.theme.TourShieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force fully transparent, dark system bars so the #121619 background
        // bleeds edge-to-edge behind status and navigation bars.
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(DarkBackground.toArgb()),
        )

        setContent {
            TourShieldTheme {
                MainNavigation(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
