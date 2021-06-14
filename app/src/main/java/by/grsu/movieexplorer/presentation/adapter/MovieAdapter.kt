package by.grsu.movieexplorer.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.data.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) /*,View.OnClickListener*/ {

        private var textViewMovieTitle: TextView = itemView.findViewById(R.id.text_movie_title)
        private var imageBtnMoviePoster: ImageButton =
            itemView.findViewById(R.id.image_movie_poster)
        private var imageBtnAddToFavourites: ImageButton =
            itemView.findViewById(R.id.image_btn_add_to_favourites)

        init {
//            imageBtnAddToFavourites.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(movie.poster)
                .into(imageBtnMoviePoster)
            textViewMovieTitle.text = movie.title
            imageBtnMoviePoster.clipToOutline = true
        }

//        override fun onClick(v: View?) {
//            when (v) {
//                imageBtnAddToFavourites -> {
//                    imageBtnAddToFavourites.setImageResource(R.drawable.ic_action_done)
//                    Toast.makeText(itemView.context, "Added to favourites", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun refreshData() {
        notifyDataSetChanged()
    }

}