package by.grsu.movieexplorer.movies.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import by.grsu.movieexplorer.movies.data.mapper.MovieMapper
import by.grsu.movieexplorer.movies.data.model.MovieCategory
import by.grsu.movieexplorer.movies.data.model.presentation.MoviePresentation
import by.grsu.movieexplorer.movies.repository.MoviesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieViewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModel(
    private val remoteMovieRepository: MoviesRepository,
    private val movieMapper: MovieMapper
) : ViewModel() {
    private val _movies: MutableLiveData<PagingData<MoviePresentation>> = MutableLiveData()
    val movies: LiveData<PagingData<MoviePresentation>>
        get() = _movies

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean>
        get() = _loading

    fun loadMovies(category: MovieCategory) {
        _loading.value = true
        remoteMovieRepository.getMovies(category.pathValue)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.map { movieDto -> movieMapper.toPresentation(movieDto) } }
            .cachedIn(viewModelScope)
            .subscribe {
                _movies.postValue(it)
                _loading.postValue(false)
            }
    }
}