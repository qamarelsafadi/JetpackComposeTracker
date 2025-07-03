package com.qamar.composescanner

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Modifier.trackRecompositions(): Modifier {
    var recompositionCount by remember { mutableIntStateOf(0) }
    val textMeasurer = rememberTextMeasurer()

    // Use SideEffect to track recompositions (increments only once per recomposition)
    SideEffect {
        recompositionCount += 1
    }

    // Draw content with a red border and recomposition count
    return this
        .then(
            Modifier.drawWithContent {
                drawContent() // Draw the original content
                val text = "Recompositions: $recompositionCount"
                val textStyle = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )

                // Draw the recomposition count text overlay on top of content
                drawText(
                    textMeasurer = textMeasurer,
                    text = text,
                    style = textStyle,
                    topLeft = Offset(
                        x = 8f,
                        y = 8f
                    )
                )
            }
        )
        .border(2.dp, Color.Red)
        .padding(16.dp)
}

/**
 * Conditionally tracks recompositions of a Composable, useful for debugging in development.
 *
 * Usage:
 * ```kotlin
 * Modifier.trackRecompositionsIf()
 * Modifier.trackRecompositionsIf(enabled = true)
 * Modifier.trackRecompositionsIf(enabled = BuildConfig.DEBUG)
 * ```
 *
 * @param enabled Whether recomposition tracking should be applied. Default is `false`.
 * You can pass `BuildConfig.DEBUG` or use your own runtime flag.
 *
 * @return The original Modifier if disabled, or a recomposition-tracking Modifier if enabled.
 */
@Composable
fun Modifier.trackRecompositionsIf(enabled: Boolean = false): Modifier {
    return if (enabled) this.trackRecompositions() else this
}