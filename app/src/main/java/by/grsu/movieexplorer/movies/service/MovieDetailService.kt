package by.grsu.movieexplorer.movies.service

import by.grsu.movieexplorer.movies.data.model.dto.MovieDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Single<MovieDto>
}