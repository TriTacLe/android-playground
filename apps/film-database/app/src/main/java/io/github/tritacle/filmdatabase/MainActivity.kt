package io.github.tritacle.filmdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.tritacle.filmdatabase.ui.theme.FilmDatabaseTheme

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
