package no.ntnu.idatt2506.filmdatabase

import kotlinx.serialization.Serializable

// Shape of one entry in res/raw/movies.json. Used for reading the file, nothing else.
@Serializable
data class RawMovie(
    val title: String,
    val director: String,
    val actors: List<String>
)
