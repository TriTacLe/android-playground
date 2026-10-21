package no.ntnu.idatt2506.filmdatabase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.ntnu.idatt2506.filmdatabase.ui.theme.FilmDatabaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    color: String,
    onChoose: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.settings)) }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorFor(color))
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.background_color),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            for (option in COLOR_CHOICES) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = option.value == color,
                            role = Role.RadioButton,
                            onClick = { onChoose(option.value) }
                        )
                        .padding(vertical = 12.dp)
                ) {
                    RadioButton(selected = option.value == color, onClick = null)
                    Text(
                        text = stringResource(option.label),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
fun SettingsScreenPreview() {
    FilmDatabaseTheme {
        SettingsScreen(color = "gronn", onChoose = {})
    }
}
