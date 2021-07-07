package by.grsu.movieexplorer.data.repository

import by.grsu.movieexplorer.data.dao.MovieDao
import by.grsu.movieexplorer.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.koin.dsl.module

val localMovieRepositoryModule = module {
    factory { LocalMovieRepository(get()) }
}

class LocalMovieRepository(
    private val movieDao: MovieDao
) {
    fun getFavouritesAsync(): Flowable<List<Movie>> = movieDao.getFavouriteMoviesAsync()
    fun insert(movie: Movie): Completable = movieDao.insertMovie(movie)
    fun getMovieById(movieId: Int): Single<Movie> = movieDao.getMovieById(movieId)
}