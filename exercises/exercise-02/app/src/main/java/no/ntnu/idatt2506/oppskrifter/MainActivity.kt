package no.ntnu.idatt2506.oppskrifter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.ntnu.idatt2506.oppskrifter.ui.theme.OppskrifterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OppskrifterTheme {
                OppskriftSkjerm()
            }
        }
    }
}
