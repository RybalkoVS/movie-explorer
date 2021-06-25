package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.presentation.activity.MainActivity
import by.grsu.movieexplorer.presentation.adapter.MovieAdapter
import by.grsu.movieexplorer.presentation.viewmodel.MovieViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class MovieListFragment : Fragment(R.layout.fragment_movie_list), MovieAdapter.OnItemClickListener {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var rvMovieList: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var favourites: List<Movie>

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
        var checkedMovies: List<Movie>?
        when (bundle.getString(getString(R.string.movie_list_type))) {
            getString(R.string.top_rated_movies) -> {
                movieViewModel.topRatedMovies.observe(viewLifecycleOwner, { movies ->
                    checkedMovies = movieViewModel.checkFavourites(movies, favourites)
                    movieAdapter = MovieAdapter(checkedMovies, this)
                    rvMovieList.adapter = movieAdapter
                })
            }
            getString(R.string.popular_movies) -> {
                movieViewModel.popularMovies.observe(viewLifecycleOwner, { movies ->
                    checkedMovies = movieViewModel.checkFavourites(movies, favourites)
                    movieAdapter = MovieAdapter(checkedMovies, this)
                    rvMovieList.adapter = movieAdapter
                })
            }
            getString(R.string.upcoming_movies) -> {
                movieViewModel.upcomingMovies.observe(viewLifecycleOwner, { movies ->
                    checkedMovies = movieViewModel.checkFavourites(movies, favourites)
                    movieAdapter = MovieAdapter(checkedMovies, this)
                    rvMovieList.adapter = movieAdapter
                })
            }
        }
    }

    override fun onStart() {
        super.onStart()
        movieViewModel.favouriteMovies
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                favourites = it
            }
    }

    override fun onItemClick(movie: Movie) {
        (activity as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MovieDetailFragment.newInstance())
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    override fun onAddToFavouritesClick(movie: Movie) {
        if (!movie.isFavourite) {
            movie.isFavourite = true
            movieViewModel.insert(movie)
            Toast.makeText(
                context,
                getString(R.string.add_to_favourites),
                Toast.LENGTH_SHORT
            ).show()
            movieAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(
                context,
                getString(R.string.already_in_favourites_notification),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        fun newInstance(): MovieListFragment {
            val fragment = MovieListFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }
}