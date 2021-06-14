package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.presentation.activity.MainActivity
import org.koin.android.ext.android.inject
import org.koin.dsl.module

val homeFragmentModule = module{
    factory { HomeFragment() }
}

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {

    private val movieListFragment by inject<MovieListFragment>()
    private lateinit var imageBtnTopRated: ImageButton
    private lateinit var imageBtnPopular: ImageButton
    private lateinit var imageBtnUpcoming: ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageBtnTopRated = view.findViewById(R.id.image_btn_top_rated)
        imageBtnPopular = view.findViewById(R.id.image_btn_popular)
        imageBtnUpcoming = view.findViewById(R.id.image_btn_upcoming)

        imageBtnTopRated.setOnClickListener(this)
        imageBtnPopular.setOnClickListener(this)
        imageBtnUpcoming.setOnClickListener(this)

        setRoundedPosters()
    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        when (v) {
            imageBtnTopRated -> {
                bundle.putString(
                    getString(R.string.movie_list_type),
                    getString(R.string.top_rated_movies)
                )
                movieListFragment.arguments = bundle
            }
            imageBtnPopular -> {
                bundle.putString(
                    getString(R.string.movie_list_type),
                    getString(R.string.popular_movies)
                )
                movieListFragment.arguments = bundle
            }
            imageBtnUpcoming -> {
                bundle.putString(
                    getString(R.string.movie_list_type),
                    getString(R.string.upcoming_movies)
                )
                movieListFragment.arguments = bundle
            }
        }
        moveToMovieListFragment()
    }

    private fun moveToMovieListFragment() {
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, movieListFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setRoundedPosters() {
        imageBtnTopRated.clipToOutline = true
        imageBtnPopular.clipToOutline = true
        imageBtnUpcoming.clipToOutline = true
    }

}