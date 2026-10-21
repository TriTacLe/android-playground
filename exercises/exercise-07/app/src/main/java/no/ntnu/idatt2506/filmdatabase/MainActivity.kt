package no.ntnu.idatt2506.filmdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.ntnu.idatt2506.filmdatabase.ui.theme.FilmDatabaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmDatabaseTheme {
                MovieApp()
            }
        }
    }
}
