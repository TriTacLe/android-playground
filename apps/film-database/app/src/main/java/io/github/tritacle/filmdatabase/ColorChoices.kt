package io.github.tritacle.filmdatabase

import androidx.annotation.StringRes

// The stored value and the shown label are kept apart. The value goes into DataStore
// and never changes; the label is a string resource and follows the language.
data class ColorChoice(val value: String, @param:StringRes val label: Int)

val COLOR_CHOICES = listOf(
    ColorChoice(SettingsStore.DEFAULT_COLOR, R.string.color_none),
    ColorChoice("bla", R.string.color_blue),
    ColorChoice("gronn", R.string.color_green),
    ColorChoice("rod", R.string.color_red)
)
