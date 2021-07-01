package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.grsu.movieexplorer.R

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    companion object{
        fun newInstance(): MovieDetailFragment{
            val fragment = MovieDetailFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}