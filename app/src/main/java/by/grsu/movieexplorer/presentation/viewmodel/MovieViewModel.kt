package by.grsu.movieexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import by.grsu.movieexplorer.data.repository.MovieRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieViewModelModule = module {
    viewModel { MovieViewModel(get()) }
}

class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val topRatedMovies = liveData {
        emit(movieRepository.getTopRatedMovies())
    }

    val popularMovies = liveData {
        emit(movieRepository.getPopularMovies())
    }

    val upcomingMovies = liveData {
        emit(movieRepository.getUpcomingMovies())
    }

}