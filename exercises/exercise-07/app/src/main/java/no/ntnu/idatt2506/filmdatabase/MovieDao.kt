package no.ntnu.idatt2506.filmdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    // IGNORE leaves a row that is already there alone. The return value is then -1
    // instead of an id, and the caller has to look the id up.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieActor(link: MovieActor)

    @Query("SELECT id FROM person WHERE name = :name")
    suspend fun personId(name: String): Long?

    @Query("SELECT id FROM movie WHERE title = :title")
    suspend fun movieId(title: String): Long?

    @Query("SELECT title FROM movie ORDER BY title")
    suspend fun allMovies(): List<String>

    @Query(
        "SELECT DISTINCT person.name FROM person " +
            "JOIN movie ON movie.directorId = person.id " +
            "ORDER BY person.name"
    )
    suspend fun allDirectors(): List<String>

    @Query(
        "SELECT movie.title FROM movie " +
            "JOIN person ON movie.directorId = person.id " +
            "WHERE person.name = :director ORDER BY movie.title"
    )
    suspend fun moviesByDirector(director: String): List<String>

    @Query(
        "SELECT person.name FROM person " +
            "JOIN movie_actor ON movie_actor.personId = person.id " +
            "JOIN movie ON movie.id = movie_actor.movieId " +
            "WHERE movie.title = :title ORDER BY person.name"
    )
    suspend fun actorsInMovie(title: String): List<String>
}
