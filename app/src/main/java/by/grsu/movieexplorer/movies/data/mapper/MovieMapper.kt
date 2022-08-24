package by.grsu.movieexplorer.movies.data.mapper

import by.grsu.movieexplorer.movies.data.model.dto.MovieDto
import by.grsu.movieexplorer.movies.data.model.presentation.MoviePresentation
import by.grsu.movieexplorer.retrofit.ApiConstants
import org.koin.dsl.module

val movieMapperModule  = module {
    single { MovieMapper() }
}

class MovieMapper {

    fun toPresentation(movieDto: MovieDto): MoviePresentation = MoviePresentation(
        movieDto.id,
        movieDto.title,
        movieDto.overview,
        ApiConstants.POSTER_BASE_URL + movieDto.poster
    )
}