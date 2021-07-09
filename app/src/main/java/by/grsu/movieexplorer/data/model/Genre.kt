package by.grsu.movieexplorer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey
    var genreId:Int? = null,
    var name:String? = null
) {
}