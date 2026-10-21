package io.github.tritacle.filmdatabase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.tritacle.filmdatabase.ui.theme.FilmDatabaseTheme

@Composable
fun MovieScreen(
    rows: List<String>,
    view: MovieView,
    choice: String,
    directors: List<String>,
    movies: List<String>,
    localPath: String,
    color: String,
    onChooseView: (MovieView) -> Unit,
    onChooseItem: (String) -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MovieTopBar(
                view = view,
                onChooseView = onChooseView,
                onOpenSettings = onOpenSettings
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorFor(color))
                .padding(horizontal = 16.dp)
        ) {
            when (view) {
                MovieView.ALL_MOVIES -> Unit
                MovieView.MOVIES_BY_DIRECTOR -> ItemPicker(
                    label = stringResource(R.string.pick_director),
                    choice = choice,
                    items = directors,
                    onChoose = onChooseItem
                )
                MovieView.ACTORS_IN_MOVIE -> ItemPicker(
                    label = stringResource(R.string.pick_movie),
                    choice = choice,
                    items = movies,
                    onChoose = onChooseItem
                )
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(rows) { row ->
                    Text(
                        text = row,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }

            Text(
                text = stringResource(R.string.local_file, localPath),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
private fun ItemPicker(
    label: String,
    choice: String,
    items: List<String>,
    onChoose: (String) -> Unit
) {
    var open by remember { mutableStateOf(false) }

    // The menu anchors to whatever it sits inside, so the button and the menu share a Box.
    Box(modifier = Modifier.padding(top = 12.dp)) {
        OutlinedButton(onClick = { open = true }, modifier = Modifier.fillMaxWidth()) {
            Text(if (choice.isEmpty()) label else choice)
        }
        DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
            for (item in items) {
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        open = false
                        onChoose(item)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
fun MovieScreenPreview() {
    FilmDatabaseTheme {
        MovieScreen(
            rows = listOf("Alien", "Blade Runner", "Fargo"),
            view = MovieView.ALL_MOVIES,
            choice = "",
            directors = listOf("Ridley Scott"),
            movies = listOf("Alien"),
            localPath = "/data/user/0/io.github.tritacle.filmdatabase/files/movies_local.json",
            color = "bla",
            onChooseView = {},
            onChooseItem = {},
            onOpenSettings = {}
        )
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
fun MovieScreenByDirectorPreview() {
    FilmDatabaseTheme {
        MovieScreen(
            rows = listOf("Alien", "Blade Runner"),
            view = MovieView.MOVIES_BY_DIRECTOR,
            choice = "Ridley Scott",
            directors = listOf("Joel Coen", "Ridley Scott"),
            movies = listOf("Alien", "Blade Runner"),
            localPath = "/data/user/0/io.github.tritacle.filmdatabase/files/movies_local.json",
            color = "gronn",
            onChooseView = {},
            onChooseItem = {},
            onOpenSettings = {}
        )
    }
}
