package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.presentation.adapter.MovieAdapter
import by.grsu.movieexplorer.presentation.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val movieListFragmentModule = module {
    factory { MovieListFragment() }
}

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var rvMovieList: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMovieList = view.findViewById(R.id.movie_list)
        rvMovieList.layoutManager = GridLayoutManager(context, 2)

        val bundle = this.arguments
        if (bundle != null) {
            getCorrectMovieList(bundle)
        }
    }

    private fun getCorrectMovieList(bundle: Bundle) {
        when (bundle.getString(getString(R.string.movie_list_type))) {
            getString(R.string.top_rated_movies) -> {
                movieViewModel.topRatedMovies.observe(viewLifecycleOwner, {
                    rvMovieList.adapter = MovieAdapter(it)
                })
            }
            getString(R.string.popular_movies) -> {
                movieViewModel.popularMovies.observe(viewLifecycleOwner, {
                    rvMovieList.adapter = MovieAdapter(it)
                })
            }
            getString(R.string.upcoming_movies) -> {
                movieViewModel.upcomingMovies.observe(viewLifecycleOwner, {
                    rvMovieList.adapter = MovieAdapter(it)
                })
            }
        }
    }
}