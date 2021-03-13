package theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val robotoCus = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold
    ),

    body2 = TextStyle(
        fontFamily = FontFamily.Default
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily.Default,
        color = Color.Gray
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily.Default,
    )
)
