package by.grsu.movieexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.data.repository.FavouriteMoviesRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteMoviesViewModelModule = module {
    viewModel { FavouriteMoviesViewModel(get()) }
}

class FavouriteMoviesViewModel(
    private val favouriteMoviesRepository: FavouriteMoviesRepository
) : ViewModel() {

    val favouriteMovies = liveData {
        emit(favouriteMoviesRepository.getAllFavourites())
    }

    fun insert(movie: Movie) {
        favouriteMoviesRepository.insert(movie)
    }
}