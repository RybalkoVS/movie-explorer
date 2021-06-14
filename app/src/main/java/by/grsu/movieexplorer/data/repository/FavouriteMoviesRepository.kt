package by.grsu.movieexplorer.data.repository

import androidx.lifecycle.LiveData
import by.grsu.movieexplorer.data.dao.MovieDao
import by.grsu.movieexplorer.data.model.Movie
import org.koin.dsl.module

val favouriteMoviesRepositoryModule = module {
    factory { FavouriteMoviesRepository(get()) }
}

class FavouriteMoviesRepository(
    private val movieDao: MovieDao
) {
    fun getAllFavourites(): LiveData<List<Movie>> = movieDao.getAll()

    fun insert(movie: Movie) = movieDao.insertMovie(movie)
}