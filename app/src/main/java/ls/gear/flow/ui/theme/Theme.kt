package ls.gear.flow.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = GreenBright,
    onPrimary = Color.White,
    secondary = LabelColor,
    tertiary = TertiaryDark,
    background = BackgroundColor,
    onBackground = Color.Black,
    onSurface = OnSurface,
    error = ErrorColor,
    onSurfaceVariant = OnSurfaceVariant,
    outlineVariant = Color.Black.copy(alpha = 0.12f)
)

private val LightColorScheme = lightColorScheme(
    primary = GreenBright,
    onPrimary = Color.White,
    secondary = LabelColor,
    tertiary = TertiaryLight,
    background = BackgroundColor,
    onBackground = Color.Black,
    onSurface = OnSurface,
    error = ErrorColor,
    onSurfaceVariant = OnSurfaceVariant,
    outlineVariant = Color.Black.copy(alpha = 0.12f)
)

@Composable
fun GearFlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
