package io.github.tritacle.socketchat.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val Colors = lightColorScheme(
    primary = Indigo,
    onPrimary = Mist,
    secondary = LightIndigo,
    background = Mist,
    onBackground = DarkInk,
    surface = PaleMist,
    onSurface = DarkInk,
    onSurfaceVariant = LightIndigo
)

@Composable
fun SocketChatTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Colors,
        typography = AppTypography,
        content = content
    )
}
