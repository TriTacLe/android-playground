package io.github.tritacle.friendeditor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.tritacle.friendeditor.ui.theme.FriendEditorTheme

@Composable
fun FriendDetailScreen(
    friend: Friend,
    onEdit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        Text(text = friend.name, style = MaterialTheme.typography.headlineLarge)
        Text(text = friend.birthday, style = MaterialTheme.typography.bodyLarge)
        Button(onClick = onEdit, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = stringResource(R.string.edit_friend))
        }
        Button(onClick = onBack, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = stringResource(R.string.back_to_list))
        }
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
fun FriendDetailScreenPreview() {
    FriendEditorTheme {
        FriendDetailScreen(
            friend = Friend(name = "Kari", birthday = "01.05.1998"),
            onEdit = {},
            onBack = {}
        )
    }
}
