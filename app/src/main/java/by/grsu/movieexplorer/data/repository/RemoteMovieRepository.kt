package by.grsu.movieexplorer.data.repository

import by.grsu.movieexplorer.data.model.MovieResponse
import by.grsu.movieexplorer.data.network.MovieService
import io.reactivex.rxjava3.core.Single
import org.koin.dsl.module

val remoteMovieRepositoryModule = module {
    factory { RemoteMovieRepository(get()) }
}

class RemoteMovieRepository(
    private val movieService: MovieService
) {
    fun getTopRatedMovies(): Single<MovieResponse> = movieService.getTopRatedMovies()
    fun getPopularMovies(): Single<MovieResponse> = movieService.getPopularMovies()
    fun getUpcomingMovies(): Single<MovieResponse> = movieService.getUpcomingMovies()
}