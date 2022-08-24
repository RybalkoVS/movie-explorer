package by.grsu.movieexplorer.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import by.grsu.movieexplorer.movies.data.model.dto.MovieDto
import by.grsu.movieexplorer.movies.data.model.dto.MoviesResponseDto
import by.grsu.movieexplorer.movies.service.MovieService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

private const val FIRST_PAGE = 1
private const val MAX_PAGE = 1000

class MoviePagingSource(
    private val movieService: MovieService,
    private val movieCategory: String
) : RxPagingSource<Int, MovieDto>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieDto>> {
        val page = params.key ?: FIRST_PAGE

        return movieService.getMovies(movieCategory, page)
            .subscribeOn(Schedulers.io())
            .map { mapToLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun mapToLoadResult(
        movieResponse: MoviesResponseDto,
        page: Int
    ): LoadResult<Int, MovieDto> {
        return LoadResult.Page(
            data = movieResponse.movieList.orEmpty(),
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == MAX_PAGE) null else page + 1
        )
    }


    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}