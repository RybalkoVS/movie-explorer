package by.grsu.movieexplorer.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.data.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(
    private var movies: List<Movie>?,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
        fun onAddToFavouritesClick(movie: Movie)
    }

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var textViewMovieTitle: TextView = itemView.findViewById(R.id.text_movie_title)
        private var imageBtnMoviePoster: ImageView =
            itemView.findViewById(R.id.image_btn_movie_poster)
        private var imageBtnAddToFavourites: ImageButton =
            itemView.findViewById(R.id.image_btn_add_to_favourites)

        fun bind(movie: Movie, onItemClickListener: OnItemClickListener) {
            Glide.with(itemView.context)
                .load(movie.poster)
                .into(imageBtnMoviePoster)
            textViewMovieTitle.text = movie.title
            imageBtnMoviePoster.clipToOutline = true
            imageBtnMoviePoster.setOnClickListener {
                onItemClickListener.onItemClick(movie)
            }
            imageBtnAddToFavourites.setOnClickListener {
                onItemClickListener.onAddToFavouritesClick(movie)
            }
            if (movie.isFavourite) {
                imageBtnAddToFavourites.setImageResource(R.drawable.ic_action_done)
            } else {
                imageBtnAddToFavourites.setImageResource(R.drawable.ic_action_add)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (movies != null) {
            holder.bind(movies!![position], onItemClickListener)
        }
    }

    override fun getItemCount(): Int {
        if (movies != null) {
            return movies!!.size
        }
        return 0
    }
}