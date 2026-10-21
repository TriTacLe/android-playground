package io.github.tritacle.numbergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.tritacle.numbergame.ui.theme.NumberGameTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGameTheme {
                GameScreenWithViewModel()
            }
        }
    }
}
