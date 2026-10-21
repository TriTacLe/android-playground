package io.github.tritacle.filmdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

private const val LOCAL_FILE = "movies_local.json"

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val settings = SettingsStore(application)
    private val files = BookFiles(application)

    // Built here, so it lives as long as the ViewModel and a rotation does not make
    // a new one.
    private val dao = Room.databaseBuilder(
        application, MovieDatabase::class.java, "movies.db"
    ).build().movieDao()

    private val json = Json { ignoreUnknownKeys = true }

    private val _rows = MutableStateFlow<List<String>>(emptyList())
    val rows: StateFlow<List<String>> = _rows.asStateFlow()

    private val _directors = MutableStateFlow<List<String>>(emptyList())
    val directors: StateFlow<List<String>> = _directors.asStateFlow()

    private val _movies = MutableStateFlow<List<String>>(emptyList())
    val movies: StateFlow<List<String>> = _movies.asStateFlow()

    private val _view = MutableStateFlow(MovieView.ALL_MOVIES)
    val view: StateFlow<MovieView> = _view.asStateFlow()

    private val _choice = MutableStateFlow("")
    val choice: StateFlow<String> = _choice.asStateFlow()

    private val _localPath = MutableStateFlow("")
    val localPath: StateFlow<String> = _localPath.asStateFlow()

    private val _color = MutableStateFlow(SettingsStore.DEFAULT_COLOR)
    val color: StateFlow<String> = _color.asStateFlow()

    init {
        viewModelScope.launch {
            _color.value = settings.backgroundColor()

            val lines = files.readPacked(R.raw.movies)
            files.writeLocal(LOCAL_FILE, lines)
            _localPath.value = files.localPath(LOCAL_FILE)

            store(json.decodeFromString<List<RawMovie>>(lines.joinToString("\n")))

            _directors.value = dao.allDirectors()
            _movies.value = dao.allMovies()
            _rows.value = dao.allMovies()
        }
    }

    fun chooseView(view: MovieView) {
        _view.value = view
        _choice.value = ""
        viewModelScope.launch { show() }
    }

    fun chooseItem(name: String) {
        _choice.value = name
        viewModelScope.launch { show() }
    }

    fun chooseColor(value: String) {
        viewModelScope.launch {
            settings.setBackgroundColor(value)
            _color.value = value
        }
    }

    private suspend fun show() {
        val name = _choice.value
        _rows.value = when (_view.value) {
            MovieView.ALL_MOVIES -> dao.allMovies()
            MovieView.MOVIES_BY_DIRECTOR ->
                if (name.isEmpty()) emptyList() else dao.moviesByDirector(name)
            MovieView.ACTORS_IN_MOVIE ->
                if (name.isEmpty()) emptyList() else dao.actorsInMovie(name)
        }
    }

    // Runs on every start. IGNORE and the unique indices are what keep the second
    // start from doubling everything.
    private suspend fun store(raw: List<RawMovie>) {
        for (movie in raw) {
            val directorId = personId(movie.director)
            dao.insertMovie(Movie(title = movie.title, directorId = directorId))
            val movieId = checkNotNull(dao.movieId(movie.title))
            for (actor in movie.actors) {
                dao.insertMovieActor(MovieActor(movieId, personId(actor)))
            }
        }
    }

    // IGNORE returns -1 when the person is already there, and then the id has to be
    // fetched with its own query.
    private suspend fun personId(name: String): Long {
        val id = dao.insertPerson(Person(name = name))
        return if (id != -1L) id else checkNotNull(dao.personId(name))
    }
}
