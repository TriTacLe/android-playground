package io.github.tritacle.recipecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.tritacle.recipecard.ui.theme.RecipeCardTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeCardTheme {
                RecipeScreen()
            }
        }
    }
}
