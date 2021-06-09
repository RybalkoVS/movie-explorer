package by.grsu.movieexplorer.data.network

import by.grsu.movieexplorer.data.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): List<Movie>
}