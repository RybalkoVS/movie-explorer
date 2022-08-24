package by.grsu.movieexplorer.movies.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.movies.data.model.presentation.MoviePresentation
import by.grsu.movieexplorer.movies.viewmodel.MovieDetailViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    companion object {
        const val MOVIE_ID = "movie id"
    }

    private var posterView: ImageView? = null
    private var movieTitle: TextView? = null
    private var movieOverview: TextView? = null
    private var progressBar: ProgressBar? = null
    private var movieId: Int? = arguments?.getInt(MOVIE_ID)
    private val movieDetailViewModel by viewModel<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId = arguments?.getInt(MOVIE_ID)

        initViews(view)
        setupObservers()
        requestMovieInfo()
    }

    private fun initViews(rootView: View) {
        with(rootView) {
            posterView = findViewById(R.id.image_poster)
            movieTitle = findViewById(R.id.text_movie_title)
            movieOverview = findViewById(R.id.text_movie_overview)
            progressBar = findViewById(R.id.progress_bar)
        }
    }

    private fun requestMovieInfo() {
        movieId?.let {
            movieDetailViewModel.getMovieDetail(it)
        }
    }

    private fun setupObservers() {
        movieDetailViewModel.movieInfo.observe(viewLifecycleOwner) {
            updateMovieInfo(it)
        }
        movieDetailViewModel.loadingState.observe(viewLifecycleOwner) {
            setProgressBarVisibility(it)
        }
    }

    private fun updateMovieInfo(movie: MoviePresentation) {
        posterView?.let {
            Glide.with(this)
                .load(movie.poster)
                .into(it)
        } ?: run {
            Log.e(MovieDetailFragment::class.simpleName, "Poster view is not initialized")
        }
        movieTitle?.text = movie.title
        movieOverview?.text = movie.overview
    }

    private fun setProgressBarVisibility(isVisible: Boolean) {
        progressBar?.isVisible = isVisible
    }

    override fun onDestroy() {
        posterView = null
        movieTitle = null
        movieOverview = null
        progressBar = null

        super.onDestroy()
    }
}