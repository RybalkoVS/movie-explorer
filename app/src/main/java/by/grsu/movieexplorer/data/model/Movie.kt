package by.grsu.movieexplorer.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    var id:Int? = null,
    @SerializedName("title")
    var title:String? = null,
//    var overview:String? = null,
//    var genres:List<Genre>? = null,
//    var isFavourite:Boolean = false
){
    @SerializedName("poster_path")
    var poster:String? = null
    set(value) {
        field = "https://image.tmdb.org/t/p/original/$value"
    }

//    var trailerLink:String? = null
//    set(value) {
//        field = "https://www.youtube.com/watch?v=$value"
//    }

}