package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.data.model.Movie
import by.grsu.movieexplorer.presentation.adapter.MovieAdapter
import by.grsu.movieexplorer.presentation.viewmodel.TopRatedMoviesViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val movieListFragmentModule = module {
    factory { MovieListFragment() }
}

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val topRatedMoviesViewModel by viewModel<TopRatedMoviesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie_list.layoutManager = GridLayoutManager(context, 2)
        topRatedMoviesViewModel.movies.observe(viewLifecycleOwner,{
            movie_list.adapter = MovieAdapter(it)
        })
    }

}