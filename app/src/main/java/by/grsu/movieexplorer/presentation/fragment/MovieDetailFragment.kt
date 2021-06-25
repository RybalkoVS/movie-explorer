package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.grsu.movieexplorer.R
import org.koin.dsl.module

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