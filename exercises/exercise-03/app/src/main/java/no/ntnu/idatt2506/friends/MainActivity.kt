package no.ntnu.idatt2506.friends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import no.ntnu.idatt2506.friends.ui.theme.FriendsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriendsTheme {
                FriendScreenOwner()
            }
        }
    }
}
