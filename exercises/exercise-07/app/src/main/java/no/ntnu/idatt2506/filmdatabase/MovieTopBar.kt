package no.ntnu.idatt2506.filmdatabase

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import no.ntnu.idatt2506.filmdatabase.ui.theme.FilmDatabaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTopBar(
    view: MovieView,
    onChooseView: (MovieView) -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    var open by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        modifier = modifier,
        actions = {
            TextButton(onClick = { open = true }) {
                Text(stringResource(view.label))
            }
            DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                for (choice in MovieView.entries) {
                    DropdownMenuItem(
                        text = { Text(stringResource(choice.label)) },
                        onClick = {
                            open = false
                            onChooseView(choice)
                        }
                    )
                }
            }
            IconButton(onClick = onOpenSettings) {
                Icon(
                    painter = painterResource(R.drawable.settings),
                    contentDescription = stringResource(R.string.settings)
                )
            }
        }
    )
}

@Preview
@Composable
fun MovieTopBarPreview() {
    FilmDatabaseTheme {
        MovieTopBar(view = MovieView.ALL_MOVIES, onChooseView = {}, onOpenSettings = {})
    }
}
