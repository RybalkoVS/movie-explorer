package by.grsu.movieexplorer.data.repository

import by.grsu.movieexplorer.data.dao.MovieDao
import by.grsu.movieexplorer.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import org.koin.dsl.module

val favouriteMovieRepositoryModule = module {
    factory { FavouriteMovieRepository(get()) }
}

class FavouriteMovieRepository(
    private val movieDao: MovieDao
) {
    fun getFavouritesAsync(): Flowable<List<Movie>> = movieDao.getFavouriteMoviesAsync()
    fun insert(movie: Movie): Completable = movieDao.insertMovie(movie)
}