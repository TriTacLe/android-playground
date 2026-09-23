package no.ntnu.idatt2506.numbergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.ntnu.idatt2506.numbergame.ui.theme.NumberGameTheme

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
