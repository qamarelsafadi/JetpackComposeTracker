package com.qamar.composescanner

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qamar.composescanner.Border
import com.qamar.composescanner.RecompositionTrackerTheme

@Composable
fun Modifier.trackRecompositions(
    border: Border = RecompositionTrackerTheme.border,
    textStyle: RecompositionTextStyle = RecompositionTrackerTheme.textStyle,
    contentPadding: PaddingValues = RecompositionTrackerTheme.contentPadding
): Modifier {
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
                val text = "${textStyle.prefix}$recompositionCount"

                // Draw the recomposition count text overlay on top of content
                drawText(
                    textMeasurer = textMeasurer,
                    text = text,
                    style = textStyle.style,
                    topLeft = textStyle.offset
                )
            }
        )
        .border(
            width = border.width,
            color = border.color,
            shape = border.shape
        )
        .padding(contentPadding)
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
fun Modifier.trackRecompositionsIf(
    enabled: Boolean = RecompositionTrackerTheme.enabled,
    border: Border = RecompositionTrackerTheme.border,
    textStyle: RecompositionTextStyle = RecompositionTrackerTheme.textStyle,
    contentPadding: PaddingValues = RecompositionTrackerTheme.contentPadding
): Modifier {
    return if (enabled) this.trackRecompositions(border, textStyle, contentPadding) else this
}