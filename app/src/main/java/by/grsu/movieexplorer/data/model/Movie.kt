package by.grsu.movieexplorer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    var isFavourite: Boolean = false
) {
    @SerializedName("poster_path")
    var poster: String? = null
        get() = "https://image.tmdb.org/t/p/original$field"

    var trailerLink: String? = null
        get() = "https://www.youtube.com/watch?v=$field"
}