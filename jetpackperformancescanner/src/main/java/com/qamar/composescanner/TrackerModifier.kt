package com.qamar.composescanner

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.trackRecompositions(): Modifier {
    val recompositionCount = remember { mutableStateOf(0) }

    // Use SideEffect to track recompositions (increments only once per recomposition)
    SideEffect {
        recompositionCount.value += 1
    }


    // Draw content with a red border and recomposition count
    return this
        .then(
            Modifier.drawWithContent {
                drawContent() // Draw the original content
                val text = "Recompositions: ${recompositionCount.value}"
                drawIntoCanvas { canvas ->
                    val paint = android.graphics
                        .Paint()
                        .apply {
                            textSize = 40f
                            color = android.graphics.Color.RED
                            isAntiAlias = true
                        }
                    // Draw the recomposition count text below the content
                    canvas.nativeCanvas.drawText(
                        text,
                        10f,
                        size.height + 40f,
                        paint
                    )
                }
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