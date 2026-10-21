package no.ntnu.idatt2506.filmdatabase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: MovieViewModel = viewModel()

    // Both destinations read the same colour, so nothing has to be passed back.
    val color by viewModel.color.collectAsStateWithLifecycle()

    NavHost(navController, startDestination = MovieList, modifier = modifier) {
        composable<MovieList> {
            val rows by viewModel.rows.collectAsStateWithLifecycle()
            val view by viewModel.view.collectAsStateWithLifecycle()
            val choice by viewModel.choice.collectAsStateWithLifecycle()
            val directors by viewModel.directors.collectAsStateWithLifecycle()
            val movies by viewModel.movies.collectAsStateWithLifecycle()
            val localPath by viewModel.localPath.collectAsStateWithLifecycle()

            MovieScreen(
                rows = rows,
                view = view,
                choice = choice,
                directors = directors,
                movies = movies,
                localPath = localPath,
                color = color,
                onChooseView = viewModel::chooseView,
                onChooseItem = viewModel::chooseItem,
                onOpenSettings = { navController.navigate(Settings) }
            )
        }
        composable<Settings> {
            SettingsScreen(
                color = color,
                onChoose = viewModel::chooseColor
            )
        }
    }
}
