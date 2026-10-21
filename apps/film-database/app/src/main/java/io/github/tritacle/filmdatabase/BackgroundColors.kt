package io.github.tritacle.filmdatabase

import androidx.compose.ui.graphics.Color

// The stored value is a string. Turning it into a colour belongs here, not in the
// ViewModel.
fun colorFor(value: String): Color = when (value) {
    "bla" -> Color(0xFFB8D3FF)
    "gronn" -> Color(0xFFCCFCCF)
    "rod" -> Color(0xFFFCCFCC)
    else -> Color.Transparent
}
