package no.ntnu.idatt2506.friends

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.ntnu.idatt2506.friends.ui.theme.FriendsTheme

// Takes everything it draws and every event it raises. No state of its own.
@Composable
fun FriendScreen(
    friends: List<Friend>,
    name: String,
    birthday: String,
    onNameChange: (String) -> Unit,
    onBirthdayChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.registered_count, friends.size),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(modifier = Modifier.weight(1f).padding(top = 8.dp)) {
            items(friends) { friend ->
                FriendItem(friend = friend)
            }
        }
        AddFriendForm(
            name = name,
            birthday = birthday,
            onNameChange = onNameChange,
            onBirthdayChange = onBirthdayChange,
            onAdd = onAdd
        )
    }
}

@Preview(showBackground = true, heightDp = 640)
@Composable
fun FriendScreenPreview() {
    FriendsTheme {
        FriendScreen(
            friends = listOf(
                Friend(name = "Kari", birthday = "01.05.1998"),
                Friend(name = "Ola", birthday = "14.11.1997")
            ),
            name = "",
            birthday = "",
            onNameChange = {},
            onBirthdayChange = {},
            onAdd = {}
        )
    }
}
