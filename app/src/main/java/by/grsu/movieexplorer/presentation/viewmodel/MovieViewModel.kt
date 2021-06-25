package by.grsu.movieexplorer.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.data.repository.FavouriteMoviesRepository
import by.grsu.movieexplorer.data.repository.MovieRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieViewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
}

class MovieViewModel(
    private val movieRepository: MovieRepository,
    private val favouriteMoviesRepository: FavouriteMoviesRepository
) : ViewModel() {

    private var movies: List<Movie>? = null

    val topRatedMovies = liveData {
        movies = movieRepository.getTopRatedMovies()
        emit(movies)
    }

    val popularMovies = liveData {
        movies = movieRepository.getPopularMovies()
        emit(movies)
    }

    val upcomingMovies = liveData {
        movies = movieRepository.getUpcomingMovies()
        emit(movies)
    }

    val favouriteMovies = favouriteMoviesRepository.getFavouritesAsync()

    fun insert(movie: Movie) {
        favouriteMoviesRepository.insert(movie)
            .subscribeOn(Schedulers.newThread())
            .subscribe {
                Log.i("Insert", "Inserted")
            }
    }

    fun checkFavourites(movieList: List<Movie>?, favourites: List<Movie>): List<Movie>? {
        if (movieList != null) {
            for (movie in movieList) {
                for (favourite in favourites) {
                    if (favourite.title == movie.title) {
                        movie.isFavourite = true
                    }
                }
            }
        }
        return movieList
    }
}