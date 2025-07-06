package com.qamar.jetpackcomposscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import com.qamar.composescanner.Border
import com.qamar.composescanner.RecompositionTrackerProperties

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecompositionTrackerProperties.apply {
                enabled = true
                style = this.style.copy(
                    border = Border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
            RecompositionTrackerScreen()
        }
    }
}
