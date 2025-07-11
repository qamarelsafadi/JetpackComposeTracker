package com.qamar.composescanner

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Border(
    val width: Dp,
    val color: Color,
    val shape: Shape = RectangleShape
)

data class RecompositionTextStyle(
    val prefix: String,
    val style: TextStyle,
    val offset: Offset,
)

/**
 * @param border The border to apply to the composable.
 * @param textStyle The text to display in the border.
 * @param contentPadding The padding to apply to the composable's content.
 */
data class RecompositionTrackerStyle(
    val border: Border,
    val textStyle: RecompositionTextStyle,
    val contentPadding: PaddingValues
) {
    companion object {
        val DEFAULT = RecompositionTrackerStyle(
            border = Border(
                width = 2.dp,
                color = Color.Red
            ),
            textStyle = RecompositionTextStyle(
                prefix = "Recompositions: ",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                ),
                offset = Offset(
                    x = 8f,
                    y = 8f
                )
            ),
            contentPadding = PaddingValues(16.dp)
        )
    }
}

/**
 * @param enabled Whether recomposition tracking should be applied to the composable.
 * @param style The style to apply to [trackRecompositions] modifier
 */
data object RecompositionTrackerProperties {
    var enabled: Boolean = false
    var style = RecompositionTrackerStyle.DEFAULT
}

