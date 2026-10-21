package io.github.tritacle.filmdatabase

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// directorId points at a row in person. Room does not join the tables by itself,
// the query does.
@Entity(tableName = "movie", indices = [Index(value = ["title"], unique = true)])
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val directorId: Long
)
