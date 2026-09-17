package no.ntnu.idatt2506.friendsnav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.ntnu.idatt2506.friendsnav.ui.theme.FriendsNavTheme

@Composable
fun FriendListScreen(
    friends: List<Friend>,
    onFriendClick: (Int) -> Unit,
    onAddClick: () -> Unit,
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
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 8.dp)
        ) {
            itemsIndexed(friends) { index, friend ->
                FriendRow(friend = friend, onClick = { onFriendClick(index) })
            }
        }
        Button(onClick = onAddClick) {
            Text(text = stringResource(R.string.add_friend))
        }
    }
}

@Composable
private fun FriendRow(friend: Friend, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = friend.name, style = MaterialTheme.typography.bodyLarge)
        Text(
            text = friend.birthday,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
fun FriendListScreenPreview() {
    FriendsNavTheme {
        FriendListScreen(
            friends = listOf(
                Friend(name = "Kari", birthday = "01.05.1998"),
                Friend(name = "Ola", birthday = "14.11.1997")
            ),
            onFriendClick = {},
            onAddClick = {}
        )
    }
}
