package by.grsu.movieexplorer.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.grsu.movieexplorer.movies.data.mapper.MovieMapper
import by.grsu.movieexplorer.movies.data.model.presentation.MoviePresentation
import by.grsu.movieexplorer.movies.repository.MovieDetailRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailViewModelModule = module {
    viewModel { MovieDetailViewModel(get(), get()) }
}

class MovieDetailViewModel(
    private val movieDetailRepository: MovieDetailRepository,
    private val movieMapper: MovieMapper
) : ViewModel() {

    private val _movieInfo: MutableLiveData<MoviePresentation> = MutableLiveData()
    val movieInfo: LiveData<MoviePresentation>
        get() = _movieInfo

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean>
        get() = _loading

    fun getMovieDetail(movieId: Int) {
        _loading.value = true
        movieDetailRepository.getMovieDetails(movieId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { movieMapper.toPresentation(it) }
            .subscribe({
                _movieInfo.postValue(it)
                _loading.value = false
            }, {
                _loading.value = false
            })
    }
}