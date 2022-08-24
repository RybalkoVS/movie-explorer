package by.grsu.movieexplorer.movies.data.model.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponseDto(
    @SerializedName("results")
    var movieList: List<MovieDto>? = null
)