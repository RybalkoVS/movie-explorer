package by.grsu.movieexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.data.repository.MovieRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val topRatedMoviesViewModelModule = module {
    viewModel { get()}
}

class TopRatedMoviesViewModel(private val movieRepository: MovieRepository):ViewModel() {

    //suspend fun getTopRatedMovies():List<Movie> = movieRepository.getTopRatedMovies()
}