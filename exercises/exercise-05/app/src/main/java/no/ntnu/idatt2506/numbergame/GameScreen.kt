package no.ntnu.idatt2506.numbergame

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import no.ntnu.idatt2506.numbergame.ui.theme.NumberGameTheme

@Composable
fun GameScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel()
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val guess by viewModel.guess.collectAsStateWithLifecycle()
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val registered by viewModel.registered.collectAsStateWithLifecycle()

    GameScreen(
        name = name,
        cardNumber = cardNumber,
        guess = guess,
        messages = messages,
        registered = registered,
        onNameChange = { viewModel.changeName(it) },
        onCardNumberChange = { viewModel.changeCardNumber(it) },
        onGuessChange = { viewModel.changeGuess(it) },
        onStartGame = { viewModel.startGame() },
        onSendGuess = { viewModel.sendGuess() },
        onNewGame = { viewModel.newGame() },
        modifier = modifier
    )
}

@Composable
fun GameScreen(
    name: String,
    cardNumber: String,
    guess: String,
    messages: List<String>,
    registered: Boolean,
    onNameChange: (String) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onGuessChange: (String) -> Unit,
    onStartGame: () -> Unit,
    onSendGuess: () -> Unit,
    onNewGame: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { GameTopBar(onNewGame = onNewGame) }
    ) { innerPadding ->
        // One list so the form stays reachable when the keyboard covers half the screen.
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .imePadding()
                .padding(horizontal = 16.dp)
        ) {
            item {
                if (registered) {
                    GuessField(guess = guess, onGuessChange = onGuessChange, onSendGuess = onSendGuess)
                } else {
                    RegisterFields(
                        name = name,
                        cardNumber = cardNumber,
                        onNameChange = onNameChange,
                        onCardNumberChange = onCardNumberChange,
                        onStartGame = onStartGame
                    )
                }
            }
            items(messages) { message ->
                Text(text = message, modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}

@Composable
private fun RegisterFields(
    name: String,
    cardNumber: String,
    onNameChange: (String) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onStartGame: () -> Unit
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(text = stringResource(R.string.name_label)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = cardNumber,
        onValueChange = onCardNumberChange,
        label = { Text(text = stringResource(R.string.card_label)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(onSend = { onStartGame() }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    )
    Button(onClick = onStartGame, modifier = Modifier.padding(vertical = 12.dp)) {
        Text(text = stringResource(R.string.start_game))
    }
}

@Composable
private fun GuessField(
    guess: String,
    onGuessChange: (String) -> Unit,
    onSendGuess: () -> Unit
) {
    OutlinedTextField(
        value = guess,
        onValueChange = onGuessChange,
        label = { Text(text = stringResource(R.string.guess_label)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(onSend = { onSendGuess() }),
        modifier = Modifier.fillMaxWidth()
    )
    Button(onClick = onSendGuess, modifier = Modifier.padding(vertical = 12.dp)) {
        Text(text = stringResource(R.string.send_guess))
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
fun GameScreenPreview() {
    NumberGameTheme {
        GameScreen(
            name = "Ola Nordmann",
            cardNumber = "12345",
            guess = "5",
            messages = listOf("Oppgi et tall mellom 1 og 10!", "Tallet 5 er for lite!"),
            registered = true,
            onNameChange = {},
            onCardNumberChange = {},
            onGuessChange = {},
            onStartGame = {},
            onSendGuess = {},
            onNewGame = {}
        )
    }
}
