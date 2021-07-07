package by.grsu.movieexplorer.presentation.viewmodel


import android.widget.Toast
import androidx.lifecycle.*
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.data.repository.LocalMovieRepository
import by.grsu.movieexplorer.data.repository.RemoteMovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieViewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
}

class MovieViewModel(
    private val remoteMovieRepository: RemoteMovieRepository,
    private val localMovieRepository: LocalMovieRepository
) : ViewModel() {

    private val _favourites: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _topRatedMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _popularMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _upcomingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()

    val favourites: LiveData<List<Movie>>
        get() = _favourites
    val topRatedMovies: LiveData<List<Movie>>
        get() = _topRatedMovies
    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies
    val upcomingMovies: LiveData<List<Movie>>
        get() = _upcomingMovies
    val loadingState: LiveData<Boolean>
        get() = _loading


    init {
        loadFavourites()
        loadTopRatedMovies()
        loadPopularMovies()
        loadUpcomingMovies()
        _loading.value = true
    }

    private fun loadTopRatedMovies() {
        remoteMovieRepository.getTopRatedMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _topRatedMovies.postValue(it.movieList)
                _loading.value = false
            }, {
                _loading.value = false
            })
    }

    private fun loadPopularMovies() {
        remoteMovieRepository.getPopularMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _popularMovies.postValue(it.movieList)
                _loading.value = false
            }, {
                _loading.value = false
            })
    }

    private fun loadUpcomingMovies() {
        remoteMovieRepository.getUpcomingMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _upcomingMovies.postValue(it.movieList)
                _loading.value = false
            }, {
                _loading.value = false
            })
    }

    private fun loadFavourites() {
        localMovieRepository.getFavouritesAsync()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _favourites.postValue(it) }
    }

    fun insert(movie: Movie) {
        localMovieRepository.insert(movie)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { }
    }

    fun checkFavourites(movieList: List<Movie>?, favourites: List<Movie>?): List<Movie>? {
        if (movieList != null && favourites != null) {
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