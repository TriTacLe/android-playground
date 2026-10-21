package io.github.tritacle.filmdatabase

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// Directors and actors share this table. The unique index means someone who is both
// exists once.
@Entity(tableName = "person", indices = [Index(value = ["name"], unique = true)])
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)
