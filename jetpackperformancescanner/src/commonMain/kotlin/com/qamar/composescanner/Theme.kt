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
 * @param enabled Whether recomposition tracking should be applied to the composable.
 * @param border The border to apply to the composable.
 * @param textStyle The text to display in the border.
 * @param contentPadding The padding to apply to the composable's content.
 */
data object RecompositionTrackerTheme {
    var enabled: Boolean = false

    var border: Border = Border(
        width = 2.dp,
        color = Color.Red
    )

    var textStyle: RecompositionTextStyle = RecompositionTextStyle(
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
    )

    var contentPadding: PaddingValues = PaddingValues(16.dp)
}

