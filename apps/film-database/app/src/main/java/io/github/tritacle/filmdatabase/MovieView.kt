package io.github.tritacle.filmdatabase

import androidx.annotation.StringRes

enum class MovieView(@param:StringRes val label: Int) {
    ALL_MOVIES(R.string.view_all_movies),
    MOVIES_BY_DIRECTOR(R.string.view_movies_by_director),
    ACTORS_IN_MOVIE(R.string.view_actors_in_movie)
}
