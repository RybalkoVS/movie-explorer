package by.grsu.movieexplorer.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import by.grsu.movieexplorer.presentation.activity.TabSelectedCallback
import by.grsu.movieexplorer.presentation.adapter.MovieAdapter
import by.grsu.movieexplorer.presentation.viewmodel.MovieViewModel
import by.grsu.movieexplorer.util.Constants
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list), MovieAdapter.OnItemClickListener,
    TabLayout.OnTabSelectedListener {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var tabSelectedCallback: TabSelectedCallback
    private lateinit var rvMovieList: RecyclerView
    private lateinit var loadingState: ProgressBar
    private lateinit var tabLayout: TabLayout
    private var movieAdapter: MovieAdapter? = null
    private var favourites: List<Movie>? = null
    private var selectedTab: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            tabSelectedCallback = context as TabSelectedCallback
        } catch (e: ClassCastException) {
            Log.i(MOVIE_LIST_FRAGMENT_TAG, e.message.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingState = view.findViewById(R.id.progress_bar_loading)
        tabLayout = view.findViewById(R.id.tab_layout_movie_list_types)
        tabLayout.addOnTabSelectedListener(this)
        rvMovieList = view.findViewById(R.id.movie_list)
        rvMovieList.layoutManager = GridLayoutManager(context, 2)

        getFavourites()
        setLoadingState()

        val bundle = this.arguments
        if (bundle != null) {
            selectedTab = bundle.getString(Constants.EXTRA_MOVIE_LIST_TYPE)
            if (selectedTab != null) {
                getCorrectMovieList(selectedTab!!)
            }
        }
        setInitialTabSelection()
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

    private fun getCorrectMovieList(movieListType: String) {
        var checkedMovies: List<Movie>?
        rvMovieList.adapter = null
        when (movieListType) {
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
        rvMovieList.adapter?.notifyDataSetChanged()
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

    private fun setInitialTabSelection() {
        when (selectedTab) {
            tabLayout.getTabAt(TAB_TOP_RATED)?.text -> {
                tabLayout.selectTab(tabLayout.getTabAt(TAB_TOP_RATED))
            }
            tabLayout.getTabAt(TAB_POPULAR)?.text -> {
                tabLayout.selectTab(tabLayout.getTabAt(TAB_POPULAR))
            }
            tabLayout.getTabAt(TAB_UPCOMING)?.text -> {
                tabLayout.selectTab(tabLayout.getTabAt(TAB_UPCOMING))
            }
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        selectedTab = tab?.text.toString()
        if (selectedTab != null) {
            getCorrectMovieList(selectedTab!!)
            tabSelectedCallback.getSelectedTab(selectedTab!!)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {
        onTabSelected(tab)
    }

    companion object {
        const val MOVIE_LIST_FRAGMENT_TAG = "MovieListFragment"
        const val TAB_TOP_RATED = 0
        const val TAB_POPULAR = 1
        const val TAB_UPCOMING = 2

        fun newInstance(bundle: Bundle): MovieListFragment {
            val fragment = MovieListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}