package by.grsu.movieexplorer.data.repository

import androidx.lifecycle.LiveData
import by.grsu.movieexplorer.data.dao.MovieDao
import by.grsu.movieexplorer.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.dsl.module

val favouriteMoviesRepositoryModule = module {
    factory { FavouriteMoviesRepository(get()) }
}

class FavouriteMoviesRepository(
    private val movieDao: MovieDao
) {
    fun getFavouritesAsync(): Observable<List<Movie>> = movieDao.getFavouriteMoviesAsync()
    fun insert(movie: Movie): Completable = movieDao.insertMovie(movie)
}