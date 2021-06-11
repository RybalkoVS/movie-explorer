package by.grsu.movieexplorer.data.network

import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponse

}