package io.github.tritacle.friendlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.tritacle.friendlist.ui.theme.FriendListTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriendListTheme {
                FriendScreenOwner()
            }
        }
    }
}
