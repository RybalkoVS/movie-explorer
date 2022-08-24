package by.grsu.movieexplorer.movies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import by.grsu.movieexplorer.movies.data.model.dto.MovieDto
import by.grsu.movieexplorer.movies.service.MovieService
import by.grsu.movieexplorer.paging.MoviePagingSource
import io.reactivex.rxjava3.core.Flowable
import org.koin.dsl.module

val moviesRepositoryModule = module {
    single { MoviesRepository(get()) }
}

private const val PAGE_SIZE = 20

class MoviesRepository(private val movieService: MovieService) {

    fun getMovies(category: String): Flowable<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(movieService, category) }
        ).flowable
    }
}