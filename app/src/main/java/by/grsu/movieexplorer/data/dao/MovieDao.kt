package by.grsu.movieexplorer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.grsu.movieexplorer.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getFavouriteMoviesAsync(): Observable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Completable
}