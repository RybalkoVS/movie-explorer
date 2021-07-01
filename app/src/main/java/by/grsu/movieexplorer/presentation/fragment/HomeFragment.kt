package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.presentation.activity.MainActivity


class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {

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
        val movieListFragment = MovieListFragment.newInstance()
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
        moveToMovieListFragment(movieListFragment)
    }

    private fun moveToMovieListFragment(fragment: MovieListFragment) {
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setRoundedPosters() {
        imageBtnTopRated.clipToOutline = true
        imageBtnPopular.clipToOutline = true
        imageBtnUpcoming.clipToOutline = true
    }

    companion object{

        fun newInstance(): HomeFragment{
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}