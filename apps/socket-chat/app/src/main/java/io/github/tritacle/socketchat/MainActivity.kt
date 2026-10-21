package io.github.tritacle.socketchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.tritacle.socketchat.ui.theme.SocketChatTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocketChatTheme {
                ChatScreenWithViewModel()
            }
        }
    }
}
