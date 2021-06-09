package by.grsu.movieexplorer.presentation.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.data.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var textViewMovieTitle: TextView = itemView.findViewById(R.id.text_movie_title)
        var imageBtnMoviePoster: ImageButton = itemView.findViewById(R.id.image_movie_poster)

        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(movie.poster)
                .into(imageBtnMoviePoster)
            textViewMovieTitle.text = movie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun refreshData() {
        notifyDataSetChanged()
    }
}