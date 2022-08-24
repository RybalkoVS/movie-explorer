package by.grsu.movieexplorer.movies.data.model

enum class MovieCategory(val pathValue: String) {
    Popular("popular"),
    TopRated("top_rated") {
        override fun toString(): String {
            return "Top Rated"
        }
    },
    Upcoming("upcoming"),
    Unknown("Unknown")
}