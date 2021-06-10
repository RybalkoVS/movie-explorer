package by.grsu.movieexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val topRatedMoviesViewModelModule = module {
    viewModel { TopRatedMoviesViewModel(get()) }
}

class TopRatedMoviesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movies = liveData(Dispatchers.IO) {
        emit(movieRepository.getTopRatedMovies())
    }

}