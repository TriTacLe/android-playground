package io.github.tritacle.filmdatabase

import androidx.room.Entity

// A film has several actors and an actor is in several films, so the link cannot be a
// column in either table. The pair is the primary key, so it is only stored once.
@Entity(tableName = "movie_actor", primaryKeys = ["movieId", "personId"])
data class MovieActor(
    val movieId: Long,
    val personId: Long
)
