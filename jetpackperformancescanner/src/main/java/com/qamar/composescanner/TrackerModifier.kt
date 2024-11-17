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


    val colorsMap = remember { mutableMapOf<String, Color>() }

    val uniqueModifierId = remember { Object().hashCode() }

    val assignedColor = colorsMap.getOrPut(uniqueModifierId.toString()) {
        generateRandomColor()
    }


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
                            color = assignedColor.toInt()
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
        .border(2.dp, assignedColor)
        .padding(16.dp)
}

private fun generateRandomColor(): Color {
    return Color(
        red = (0..255).random() / 255f,
        green = (0..255).random() / 255f,
        blue = (0..255).random() / 255f,
        alpha = 1f
    )
}

private fun Color.toInt(): Int {
    val alpha = (alpha * 255).toInt() and 0xFF
    val red = (red * 255).toInt() and 0xFF
    val green = (green * 255).toInt() and 0xFF
    val blue = (blue * 255).toInt() and 0xFF
    return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
}