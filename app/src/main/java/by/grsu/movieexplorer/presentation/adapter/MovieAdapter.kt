package by.grsu.movieexplorer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.databinding.MovieListItemBinding
import by.grsu.movieexplorer.data.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(binding.root.context)
                .load(movie.poster)
                .into(binding.imageMoviePoster)
            binding.textMovieTitle.text = movie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun refreshData(){
        notifyDataSetChanged()
    }
}