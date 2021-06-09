package by.grsu.movieexplorer.data.repository

import by.grsu.movieexplorer.data.network.MovieService
import org.koin.dsl.module

val movieRepositoryModule = module {
    factory { MovieRepository(get()) }
}

class MovieRepository(private val movieService: MovieService) {

    suspend fun getTopRatedMovies() = movieService.getTopRatedMovies()

}