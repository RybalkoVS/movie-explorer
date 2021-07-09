package by.grsu.movieexplorer.data.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import by.grsu.movieexplorer.data.model.Genre
import by.grsu.movieexplorer.data.model.Movie

class MovieWithGenres(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movieId",
        entity = Movie::class,
        entityColumn = "genreId",
        associateBy = Junction(
            value = MovieGenreCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    )
    val genres: List<Genre>
)
