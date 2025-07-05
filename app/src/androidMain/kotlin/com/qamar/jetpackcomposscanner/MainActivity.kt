package com.qamar.jetpackcomposscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.qamar.composescanner.Border
import com.qamar.composescanner.RecompositionTrackerTheme
import com.qamar.composescanner.RecompositionTrackerTheme.border
import com.qamar.composescanner.RecompositionTrackerTheme.enabled
import com.qamar.composescanner.RecompositionTrackerTheme.textStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecompositionTrackerTheme.apply {
                enabled = true
                border = Border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }
            RecompositionTrackerScreen()
        }
    }
}
