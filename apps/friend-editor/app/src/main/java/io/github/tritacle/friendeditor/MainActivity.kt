package io.github.tritacle.friendeditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.tritacle.friendeditor.ui.theme.FriendEditorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriendEditorTheme {
                FriendNavHost()
            }
        }
    }
}
