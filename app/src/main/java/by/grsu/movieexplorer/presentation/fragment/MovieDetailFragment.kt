package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.presentation.activity.MainActivity
import by.grsu.movieexplorer.presentation.viewmodel.MovieViewModel
import by.grsu.movieexplorer.util.Constants
import com.bumptech.glide.Glide
import kotlinx.coroutines.channels.consumesAll
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var poster:ImageView

    private var movieId: Int? = null
    private var movie: Movie? = null
    private val movieViewModel by viewModel<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun getMovie(movieId: Int) {
//        movieViewModel.movie.observe(viewLifecycleOwner, {
//            movie = it
//        })
    }

    private fun setupView(){
//        Glide.with(activity as MainActivity)
//            .load(movie?.poster)
//            .into(poster)
    }

    companion object {
        fun newInstance(): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}