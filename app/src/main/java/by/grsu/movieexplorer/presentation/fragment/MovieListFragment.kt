package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.presentation.activity.MainActivity
import by.grsu.movieexplorer.presentation.adapter.MovieAdapter
import by.grsu.movieexplorer.presentation.viewmodel.MovieViewModel
import by.grsu.movieexplorer.util.Constants
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list), MovieAdapter.OnItemClickListener {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var rvMovieList: RecyclerView
    private lateinit var loadingState: ProgressBar
    private var movieAdapter: MovieAdapter? = null
    private var favourites: List<Movie>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingState = view.findViewById(R.id.progress_bar_loading)
        rvMovieList = view.findViewById(R.id.movie_list)
        rvMovieList.layoutManager = GridLayoutManager(context, 2)

        getFavourites()
        setLoadingState()

        val bundle = this.arguments
        if (bundle != null) {
            getCorrectMovieList(bundle)
        }
    }

    override fun onItemClick(movie: Movie) {
        val movieDetailFragment = MovieDetailFragment.newInstance()
        val bundle = Bundle()
        bundle.putInt(Constants.EXTRA_MOVIE_ID, movie.id!!)
        movieDetailFragment.arguments
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
            movieAdapter?.notifyDataSetChanged()
        } else {
            Toast.makeText(
                context,
                getString(R.string.already_in_favourites_notification),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getCorrectMovieList(bundle: Bundle) {
        var checkedMovies: List<Movie>?
        when (bundle.getString(getString(R.string.movie_list_type))) {
            getString(R.string.top_rated_movies) -> {
                movieViewModel.topRatedMovies.observe(viewLifecycleOwner, {
                    checkedMovies = movieViewModel.checkFavourites(it, favourites)
                    movieAdapter = MovieAdapter(checkedMovies, this)
                    rvMovieList.adapter = movieAdapter
                })
            }
            getString(R.string.popular_movies) -> {
                movieViewModel.popularMovies.observe(viewLifecycleOwner, {
                    checkedMovies = movieViewModel.checkFavourites(it, favourites)
                    movieAdapter = MovieAdapter(checkedMovies, this)
                    rvMovieList.adapter = movieAdapter
                })
            }
            getString(R.string.upcoming_movies) -> {
                movieViewModel.upcomingMovies.observe(viewLifecycleOwner, {
                    checkedMovies = movieViewModel.checkFavourites(it, favourites)
                    movieAdapter = MovieAdapter(checkedMovies, this)
                    rvMovieList.adapter = movieAdapter
                })
            }
        }
    }

    private fun getFavourites() {
        movieViewModel.favourites.observe(viewLifecycleOwner, {
            favourites = it
        })
    }

    private fun setLoadingState() {
        movieViewModel.loadingState.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                loadingState.visibility = View.VISIBLE
            } else {
                loadingState.visibility = View.GONE
            }
        })
    }

    companion object {
        fun newInstance(): MovieListFragment {
            val fragment = MovieListFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }

}