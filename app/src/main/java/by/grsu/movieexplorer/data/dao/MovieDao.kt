package by.grsu.movieexplorer.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.grsu.movieexplorer.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): LiveData<List<Movie>>

    @Insert
    fun insertMovie(movie: Movie)
}