package by.grsu.movieexplorer.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.movies.data.model.presentation.MoviePresentation
import com.bumptech.glide.Glide

private const val IMAGE_PROGRESS_STROKE_WIDTH = 10f
private const val IMAGE_PROGRESS_RADIUS = 50f

class MovieAdapter(
    private val onItemClickListener: OnItemClickListener
) : PagingDataAdapter<MoviePresentation, MovieAdapter.MovieViewHolder>(DiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(movie: MoviePresentation)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var textViewMovieTitle: TextView = itemView.findViewById(R.id.text_movie_title)
        private var imageBtnMoviePoster: ImageView =
            itemView.findViewById(R.id.image_btn_movie_poster)

        fun bind(movie: MoviePresentation, onItemClickListener: OnItemClickListener) {
            Glide.with(itemView.context)
                .load(movie.poster)
                .placeholder(CircularProgressDrawable(itemView.context).apply {
                    strokeWidth = IMAGE_PROGRESS_STROKE_WIDTH
                    centerRadius = IMAGE_PROGRESS_RADIUS
                    start()
                })
                .into(imageBtnMoviePoster)
            textViewMovieTitle.text = movie.title

            imageBtnMoviePoster.setOnClickListener {
                onItemClickListener.onItemClick(movie)
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
        getItem(position)?.let { holder.bind(it, onItemClickListener) }
    }
}

class DiffCallback : DiffUtil.ItemCallback<MoviePresentation>() {

    override fun areItemsTheSame(
        oldItem: MoviePresentation,
        newItem: MoviePresentation
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MoviePresentation,
        newItem: MoviePresentation
    ): Boolean {
        return oldItem.id == newItem.id
    }
}