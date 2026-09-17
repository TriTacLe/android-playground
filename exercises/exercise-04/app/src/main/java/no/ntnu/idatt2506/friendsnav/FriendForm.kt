package no.ntnu.idatt2506.friendsnav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.ntnu.idatt2506.friendsnav.ui.theme.FriendsNavTheme

// One form for both adding and editing. The caller decides the heading and the button label,
// so nothing here needs to know which of the two it is doing.
@Composable
fun FriendForm(
    title: String,
    buttonLabel: String,
    name: String,
    birthday: String,
    onNameChange: (String) -> Unit,
    onBirthdayChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = stringResource(R.string.name_label)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = birthday,
            onValueChange = onBirthdayChange,
            label = { Text(text = stringResource(R.string.birthday_label)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Button(onClick = onSubmit, modifier = Modifier.padding(top = 12.dp)) {
            Text(text = buttonLabel)
        }
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
fun FriendFormPreview() {
    FriendsNavTheme {
        FriendForm(
            title = "Endre venn",
            buttonLabel = "Lagre",
            name = "Kari",
            birthday = "01.05.1998",
            onNameChange = {},
            onBirthdayChange = {},
            onSubmit = {}
        )
    }
}
