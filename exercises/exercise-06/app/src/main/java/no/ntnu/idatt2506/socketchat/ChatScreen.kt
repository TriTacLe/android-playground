package no.ntnu.idatt2506.socketchat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import no.ntnu.idatt2506.socketchat.ui.theme.SocketChatTheme

@Composable
fun ChatScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel()
) {
    val role by viewModel.role.collectAsStateWithLifecycle()
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val draft by viewModel.draft.collectAsStateWithLifecycle()
    val connected by viewModel.connected.collectAsStateWithLifecycle()

    ChatScreen(
        role = role,
        messages = messages,
        draft = draft,
        connected = connected,
        onDraftChange = { viewModel.changeDraft(it) },
        onSend = { viewModel.send() },
        onBeServer = { viewModel.startServer() },
        onConnect = { viewModel.startClient() },
        modifier = modifier
    )
}

@Composable
fun ChatScreen(
    role: Role,
    messages: List<String>,
    draft: String,
    connected: Int,
    onDraftChange: (String) -> Unit,
    onSend: () -> Unit,
    onBeServer: () -> Unit,
    onConnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { ChatTopBar(role = role, connected = connected) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .imePadding()
                .padding(horizontal = 16.dp)
        ) {
            item {
                if (role == Role.NONE) {
                    RolePicker(onBeServer = onBeServer, onConnect = onConnect)
                } else {
                    MessageField(draft = draft, onDraftChange = onDraftChange, onSend = onSend)
                }
            }
            items(messages) { message ->
                Text(text = message, modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}

@Composable
private fun RolePicker(onBeServer: () -> Unit, onConnect: () -> Unit) {
    Text(
        text = stringResource(R.string.pick_role),
        modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
    )
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(onClick = onBeServer) { Text(text = stringResource(R.string.be_server)) }
        Button(onClick = onConnect) { Text(text = stringResource(R.string.connect)) }
    }
}

@Composable
private fun MessageField(draft: String, onDraftChange: (String) -> Unit, onSend: () -> Unit) {
    OutlinedTextField(
        value = draft,
        onValueChange = onDraftChange,
        label = { Text(text = stringResource(R.string.message_label)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(onSend = { onSend() }),
        modifier = Modifier.fillMaxWidth()
    )
    Button(onClick = onSend, modifier = Modifier.padding(vertical = 12.dp)) {
        Text(text = stringResource(R.string.send))
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
fun ChatScreenPreview() {
    SocketChatTheme {
        ChatScreen(
            role = Role.SERVER,
            messages = listOf("Klient 1 koblet til", "Klient 1: hei", "Tjener: hei tilbake"),
            draft = "god morgen",
            connected = 1,
            onDraftChange = {},
            onSend = {},
            onBeServer = {},
            onConnect = {}
        )
    }
}

@Preview(showBackground = true, heightDp = 300)
@Composable
fun RolePickerPreview() {
    SocketChatTheme {
        ChatScreen(
            role = Role.NONE,
            messages = listOf(),
            draft = "",
            connected = 0,
            onDraftChange = {},
            onSend = {},
            onBeServer = {},
            onConnect = {}
        )
    }
}
