package by.grsu.movieexplorer.movies.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.movies.data.model.MovieCategory
import by.grsu.movieexplorer.movies.data.model.presentation.MoviePresentation
import by.grsu.movieexplorer.movies.presentation.adapter.MovieAdapter
import by.grsu.movieexplorer.movies.presentation.adapter.MoviesLoadStateAdapter
import by.grsu.movieexplorer.movies.presentation.fragment.MovieDetailFragment.Companion.MOVIE_ID
import by.grsu.movieexplorer.movies.viewmodel.MovieViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list), MovieAdapter.OnItemClickListener,
    TabLayout.OnTabSelectedListener {

    private val movieViewModel by viewModel<MovieViewModel>()
    private var moviesRecycleView: RecyclerView? = null
    private var progressIndicator: ProgressBar? = null
    private var movieConcatAdapter: ConcatAdapter? = null
    private var movieAdapter: MovieAdapter = MovieAdapter(this).apply {
        movieConcatAdapter = withLoadStateFooter(MoviesLoadStateAdapter(this::retry))
    }
    private var categoriesTabLayout: TabLayout? = null
    private var currentMovieCategory: MovieCategory = MovieCategory.TopRated
    private var currentTabPosition: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupObservers()
        if (movieAdapter.itemCount == 0) {
            requestMovies()
        }
    }

    override fun onStart() {
        super.onStart()

        categoriesTabLayout?.getTabAt(currentTabPosition)?.select()
    }

    private fun initViews(rootView: View) {
        progressIndicator = rootView.findViewById(R.id.progress_bar)
        moviesRecycleView = rootView.findViewById(R.id.movie_detail_fragment)
        moviesRecycleView?.layoutManager = LinearLayoutManager(context)
        moviesRecycleView?.adapter = movieConcatAdapter
        categoriesTabLayout = rootView.findViewById(R.id.tab_layout_movie_categories)
        categoriesTabLayout?.addOnTabSelectedListener(this)
    }

    private fun setupObservers() {
        movieViewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }

        movieViewModel.loadingState.observe(viewLifecycleOwner) { loading ->
            setLoadingState(loading)
        }
    }

    private fun requestMovies() {
        movieViewModel.loadMovies(category = currentMovieCategory)
    }

    private fun setLoadingState(isLoading: Boolean) {
        progressIndicator?.isVisible = isLoading
    }

    override fun onItemClick(movie: MoviePresentation) {
        val args = Bundle().apply {
            putInt(MOVIE_ID, movie.id)
        }

        findNavController().navigate(R.id.action_movie_list_fragment_to_movie_detail_fragment, args)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.let {
            currentTabPosition = it.position
            currentMovieCategory = getMovieCategory(tab.text.toString())

            if (currentMovieCategory != MovieCategory.Unknown) {
                requestMovies()
            } else {
                Log.e(MovieListFragment::class.simpleName, "Unknown movie category")
            }
        }
    }

    private fun getMovieCategory(tabTitle: String): MovieCategory {
        return when (tabTitle) {
            MovieCategory.Popular.toString() -> {
                MovieCategory.Popular
            }
            MovieCategory.Upcoming.toString() -> {
                MovieCategory.Upcoming
            }
            MovieCategory.TopRated.toString() -> {
                MovieCategory.TopRated
            }
            else -> {
                MovieCategory.Unknown
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onDestroy() {
        moviesRecycleView = null
        progressIndicator = null
        categoriesTabLayout = null

        super.onDestroy()
    }
}