package by.grsu.movieexplorer.movies.service

import by.grsu.movieexplorer.movies.data.model.dto.MoviesResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{category}/")
    fun getMovies(
        @Path("category") category: String,
        @Query("page") page: Int
    ): Single<MoviesResponseDto>
}