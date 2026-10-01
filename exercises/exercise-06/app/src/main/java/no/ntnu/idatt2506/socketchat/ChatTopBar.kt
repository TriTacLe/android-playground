package no.ntnu.idatt2506.socketchat

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import no.ntnu.idatt2506.socketchat.ui.theme.SocketChatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    role: Role,
    connected: Int,
    modifier: Modifier = Modifier
) {
    val title = when (role) {
        Role.NONE -> stringResource(R.string.app_name)
        Role.SERVER -> stringResource(R.string.title_server, connected)
        Role.CLIENT -> stringResource(R.string.title_client, connected)
    }
    TopAppBar(title = { Text(text = title) }, modifier = modifier)
}

@Preview
@Composable
fun ChatTopBarPreview() {
    SocketChatTheme {
        ChatTopBar(role = Role.SERVER, connected = 2)
    }
}
