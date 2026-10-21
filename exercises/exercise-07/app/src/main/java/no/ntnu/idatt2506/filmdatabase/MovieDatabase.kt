package no.ntnu.idatt2506.filmdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

// Version 1 and no schema directory, so exportSchema is off and the build stays quiet.
@Database(
    entities = [Person::class, Movie::class, MovieActor::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
