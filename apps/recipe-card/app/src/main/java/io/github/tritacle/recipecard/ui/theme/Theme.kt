package io.github.tritacle.recipecard.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val Farger = lightColorScheme(
    primary = Brun,
    onPrimary = Krem,
    secondary = LysBrun,
    background = Krem,
    onBackground = MorkBrun,
    surface = LysKrem,
    onSurface = MorkBrun,
    onSurfaceVariant = LysBrun
)

@Composable
fun RecipeCardTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Farger,
        typography = Typografi,
        content = content
    )
}
