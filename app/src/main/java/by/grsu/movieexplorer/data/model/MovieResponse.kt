package by.grsu.movieexplorer.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var movieList: List<Movie>
) {
}