package no.ntnu.idatt2506.socketchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.ntnu.idatt2506.socketchat.ui.theme.SocketChatTheme

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
