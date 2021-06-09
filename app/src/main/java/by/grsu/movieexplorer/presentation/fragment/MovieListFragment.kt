package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.databinding.FragmentMovieListBinding
import by.grsu.movieexplorer.presentation.adapter.MovieAdapter
import by.grsu.movieexplorer.presentation.viewmodel.TopRatedMoviesViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var binding: FragmentMovieListBinding? = null

    private val topRatedMoviesViewModel by viewModel<TopRatedMoviesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentMovieListBinding = FragmentMovieListBinding.bind(view)
        binding = fragmentMovieListBinding
        with(fragmentMovieListBinding){
            movieList.layoutManager = LinearLayoutManager(context)
        }
    }

}