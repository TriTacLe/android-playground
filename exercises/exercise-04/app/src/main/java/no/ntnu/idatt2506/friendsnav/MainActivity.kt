package no.ntnu.idatt2506.friendsnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.ntnu.idatt2506.friendsnav.ui.theme.FriendsNavTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriendsNavTheme {
                FriendNavHost()
            }
        }
    }
}
