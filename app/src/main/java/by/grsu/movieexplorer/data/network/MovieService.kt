package by.grsu.movieexplorer.data.network

import by.grsu.movieexplorer.data.model.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MovieService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Single<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Single<MovieResponse>

}