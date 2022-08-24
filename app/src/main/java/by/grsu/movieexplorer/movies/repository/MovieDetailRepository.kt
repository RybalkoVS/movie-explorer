package by.grsu.movieexplorer.movies.repository

import by.grsu.movieexplorer.movies.data.model.dto.MovieDto
import by.grsu.movieexplorer.movies.service.MovieDetailService
import io.reactivex.rxjava3.core.Single
import org.koin.dsl.module

val movieDetailRepositoryModule = module {
    single { MovieDetailRepository(get()) }
}

class MovieDetailRepository(private val movieDetailService: MovieDetailService) {

    fun getMovieDetails(movieId: Int): Single<MovieDto> {
        return movieDetailService.getMovieDetail(movieId)
    }
}