package no.ntnu.idatt2506.friends

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.ntnu.idatt2506.friends.ui.theme.FriendsTheme

@Composable
fun AddFriendForm(
    name: String,
    birthday: String,
    onNameChange: (String) -> Unit,
    onBirthdayChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = stringResource(R.string.name_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
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
        Button(
            onClick = onAdd,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(text = stringResource(R.string.add_friend))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddFriendFormPreview() {
    FriendsTheme {
        AddFriendForm(
            name = "Kari",
            birthday = "01.05.1998",
            onNameChange = {},
            onBirthdayChange = {},
            onAdd = {}
        )
    }
}
