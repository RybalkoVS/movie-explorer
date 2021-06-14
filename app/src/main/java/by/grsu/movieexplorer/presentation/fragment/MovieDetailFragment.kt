package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.grsu.movieexplorer.R
import org.koin.dsl.module

val movieDetailFragmentModule = module {
    factory { MovieDetailFragment() }
}

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {


}