package no.ntnu.idatt2506.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

// The nearest common parent of everything that needs the state, so it owns all three values.
// The fields use rememberSaveable and survive rotation. The list uses remember and does not:
// Friend is an ordinary data class that the saver cannot store, which is expected here.
@Composable
fun FriendScreenOwner(modifier: Modifier = Modifier) {
    var friends by remember { mutableStateOf(listOf<Friend>()) }
    var name by rememberSaveable { mutableStateOf("") }
    var birthday by rememberSaveable { mutableStateOf("") }

    FriendScreen(
        friends = friends,
        name = name,
        birthday = birthday,
        onNameChange = { name = it },
        onBirthdayChange = { birthday = it },
        onAdd = {
            friends = friends + Friend(name = name, birthday = birthday)
            name = ""
            birthday = ""
        },
        modifier = modifier
    )
}
